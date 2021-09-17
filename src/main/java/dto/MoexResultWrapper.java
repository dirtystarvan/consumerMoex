package dto;

public class MoexResultWrapper {
	MoexDto[] growthLeaders;
	MoexDto[] fallLeaders;

	public MoexDto[] getGrowthLeaders() {
		return growthLeaders;
	}

	public void setGrowthLeaders(MoexDto[] growthLeaders) {
		this.growthLeaders = growthLeaders;
	}

	public MoexDto[] getFallLeaders() {
		return fallLeaders;
	}

	public void setFallLeaders(MoexDto[] fallLeaders) {
		this.fallLeaders = fallLeaders;
	}
}
