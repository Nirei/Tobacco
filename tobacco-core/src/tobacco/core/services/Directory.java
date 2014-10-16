package tobacco.core.services;

public final class Directory {

	private static DebuggingService debugging = new NullDebuggingService();
	private static DataService data = new DefaultDataService();
	
	private Directory() {}

	public static DebuggingService getDebuggingService() {
		return debugging;
	}
	
	public static void setDebuggingService(DebuggingService debugging) {
		Directory.debugging = debugging;
	}
	

	public static DataService getDataService() {
		return data;
	}
	
	public static void setDataService(DataService data) {
		Directory.data = data;
	}
}
