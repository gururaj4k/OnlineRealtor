import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import uic.onlinerealtor.dataaccess.DataPointerFactory;
import uic.onlinerealtor.dataaccess.IDataPointer;
import uic.onlinerealtor.dataaccess.DataPointerFactory.DataSource;
import uic.onlinerealtor.entities.Assets;

public class QueryMongo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fstream = new FileInputStream("src\\jsonInput.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String jsonString = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				jsonString = jsonString + line;
			}
			System.out.println(jsonString);
			IDataPointer datapointer = DataPointerFactory
					.getDataSource(DataSource.NoSql);
			JSONArray jsonArrayList = datapointer.executeQuery(jsonString);
			List<Assets> assetList = retrieveAssetsFromJson(jsonArrayList);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private static List<Assets> retrieveAssetsFromJson(JSONArray assetsArray) {
		List<Assets> assetList = new ArrayList<Assets>();
		// Retrieve Details from
		System.out.println("retrieveAssetsFromJson :" + assetsArray.length());
		try {
			for (int i = 0; i < assetsArray.length(); i++) {
				JSONObject jsonObj = assetsArray.getJSONObject(i);
				Assets asset = new Assets();
				asset.setAssetKey(jsonObj.getString("assetid")
						.replace("\n", ""));
				asset.setAddressline1(jsonObj.get("addressline1").toString());
				asset.setAddressline2(jsonObj.get("addressline2").toString());
				asset.setTypesofrooms(jsonObj.get("typesofrooms").toString());
				// asset.setAmenities(jsonObj.get("amenities").toString());
				asset.setFloortype(jsonObj.get("typeFloor").toString());
				asset.setTypeofroof(jsonObj.get("typeofroof").toString());
				asset.setFullyfurnished(jsonObj.get("fullyfurnished")
						.toString());
				asset.setClubmembership(jsonObj.get("hasClubMembership")
						.toString());
				asset.setEstgasbill(Double.valueOf(jsonObj.get("Estgasbill")
						.toString()));
				asset.setDistfromdowntown(Integer.valueOf(jsonObj.get(
						"Dist from downtown").toString()));
				asset.setOwnercontact(jsonObj.get("ownercontact").toString());
				asset.setAgeofhome(Integer.valueOf(jsonObj.get("ageofhome")
						.toString()));
				asset.setSection(Integer.valueOf(jsonObj.get("section")
						.toString()));
				asset.setCity(jsonObj.get("city").toString());
				asset.setOwnername(jsonObj.get("ownername").toString());
				asset.setPrice(Double.valueOf(jsonObj.get("minPriceRange")
						.toString()));
				asset.setLatitude(Double.valueOf(jsonObj.get("latitude")
						.toString()));
				asset.setLongitude(Double.valueOf(jsonObj.get("longitude")
						.toString()));
				asset.setNoofbedrooms(Integer.valueOf(jsonObj.get(
						"noofbedrooms").toString()));
				assetList.add(asset);
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return assetList;
	}
}
