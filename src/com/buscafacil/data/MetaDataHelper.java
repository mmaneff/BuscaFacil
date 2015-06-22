package com.buscafacil.data;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;


public class MetaDataHelper {

	public enum MetaDataName {
		DATABASE_PATH("DB_PATH"), 
		DATABASE_NAME("DB_NAME"), 
		DATABASE_VERSION("DB_VERSION");
		
		private String text;
		
		MetaDataName(String text) {
			this.text = text;
		}
		
		public String toString() {
			return this.text;
		}
	}
	
	/**
	 * 
	 * @param context
	 * @param metaDataName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getMetaData(Context context, MetaDataName metaDataName) {
		try {
			final ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);

			if (ai.metaData != null) {
				return (T) ai.metaData.get(metaDataName.toString());
			}
		}
		catch (Exception e) {
			Log.w("MetaDataHelper", "Couldn't find meta-data: " + metaDataName);
		}

		return null;
	}
	
	/**
	 * 
	 * @param context
	 * @param metaData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getMetaData(Context context, String metaData) {
		try {
			final ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
					PackageManager.GET_META_DATA);

			if (ai.metaData != null) {
				return (T) ai.metaData.get(metaData);
			}
		}
		catch (Exception e) {
			Log.w("MetaDataHelper", "Couldn't find meta-data: " + metaData);
		}

		return null;
	}
	
}
