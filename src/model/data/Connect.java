package model.data;

public class Connect {

	private static IDao iDao = null;

	public Connect() {

	}

	public static IDao getIDao() {
		if(iDao == null){
			iDao = new Dao();
		}
		return iDao;
	}
}
