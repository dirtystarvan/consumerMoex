package controller;

import dto.MoexDto;
import dto.MoexResultWrapper;
import helpers.DayQuotesAnalyser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shares-consumer")
public class MoexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoexController.class);

    @PostMapping
    public MoexResultWrapper getTopSharesByDay(@RequestBody List<MoexDto> moexDto) {
//        LOGGER.info(moexDto.toString());

//        LOGGER.info(Arrays.toString(new DayQuotesAnalyser(moexDto).worstOfTheDay(3)));

        DayQuotesAnalyser dayAnalyzer = new DayQuotesAnalyser(moexDto);

        MoexResultWrapper responseBody = new MoexResultWrapper();
        responseBody.setFallLeaders(dayAnalyzer.worstOfTheDay(5));
        responseBody.setGrowthLeaders(dayAnalyzer.bestOfTheDay(5));

        return responseBody;
    }
}
