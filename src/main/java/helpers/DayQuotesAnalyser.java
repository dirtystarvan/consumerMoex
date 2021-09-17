package helpers;

import dto.MoexDto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DayQuotesAnalyser {
	private File file;
	private List<MoexDto> quotes;
	private Comparator<MoexDto> openClose = (o1, o2) -> (int)((o2.close - o2.open) - (o1.close - o1.open));

	public DayQuotesAnalyser(List<MoexDto> quotes) {
		this.quotes = quotes;
	}

	public MoexDto[] bestOfTheDay(int limit) {
		MoexDto[] result = quotes.stream()
				.filter(quote -> quote.open != null && quote.close != null)
				.filter(quote -> quote.close > quote.open)
				.sorted(openClose).limit(limit).toArray(MoexDto[]::new);

		dumpHighlight(result);

		return result;
	}

	public MoexDto[] worstOfTheDay(int limit) {
		MoexDto[] result = quotes.stream()
				.filter(quote -> quote.open != null && quote.close != null)
				.filter(quote -> quote.close < quote.open)
				.sorted(openClose.reversed()).limit(limit).toArray(MoexDto[]::new);

		dumpHighlight(result);

		return result;
	}

	private void dumpHighlight(MoexDto[] quotes) {
		if (file == null)
			file = new File("highlightQuotes.txt");

		try (BufferedWriter fw = new BufferedWriter(new FileWriter(file, true))) {
			Arrays.stream(quotes)
					.filter(q -> Math.abs((q.close - q.open) / q.open * 100) >= 5)
					.forEach(q -> {
						try {
							fw.append(q.toString());
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
