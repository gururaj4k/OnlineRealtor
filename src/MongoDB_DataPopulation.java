
import java.io.FileInputStream;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.Random;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import java.util.UUID;

public class MongoDB_DataPopulation {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws UnknownHostException{
		// TODO Auto-generated method stub
		//Connecting to the online_realtor database
		
		try{
		String database=null;
		String mongoServer=null;
		Properties prop=new Properties();
		prop.load(new FileInputStream("C:\\config.properties"));
		mongoServer=prop.getProperty("mongoDBServer");
		database=prop.getProperty("mondoDB");
		Mongo m = new Mongo(mongoServer);
		DB db = m.getDB( database);
		DBCollection coll=db.getCollection("assetdetails");
		DBCollection coll1=db.getCollection("apartment");
		DBCollection coll2=db.getCollection("independent");
		String suggested_amenities="",typeofroof="",typesofrooms="",address1="",address2="",street="",city="",state="",zipcode="",country="", ownername="",typeFloor="",fullyfurnished="",communityname="",heater="",aircond="",clubmembership="",patio="",lawn="",kitchenisland="",swimmingpool="",backyard="",hasGarage="",hasGym="",hasBasement="",isNewHome="",isFirePlaceAvailable="",isSportsRoomAvailable="",isCentralizedCoolingAvailable="",isRecreationRoomAvailable="",isDishWasherAvailable="",isCentralizedHeating="",isLaundaryAvailable="",isbalconyAvailable="",isSwimmingPoolAvailable="",hasClubMembership="",hasCentralizedHeating="",isFamilyRoomAvailable="",isGuestRoomAvailable="",isDownstairsRoomAvailable="",isOfficeRoomAvailable="",isMastersBedRoomAvailable="",location="";
		String randomuuidstring="";
		int ASSETDETAILS_RECORDS=200,APARTMENT_RECORDS=0,INDEPENDENT_RECORDS=0,ageofhome=0,insert_asset_details,insert_apartment,insert_independent,random,n,ownercontact=0,estgasbill=0,distfromdowntown=0,noofcabinets=0,section=0,doorno,maxPriceRance=0,minPriceRange=0,noofbedrooms=0,nooffloors=0,whichfloor=0,preferrableBrCount=0,noOfParkinglots=0,numberOfSprinkler=0;
		boolean assettype=true;
		double latitude=0.0,longitude=0.0;
		char c;
		Random r=new Random ();
		String[] independent_assetid=new String[ASSETDETAILS_RECORDS],apartment_assetid=new String[ASSETDETAILS_RECORDS];
		independent_assetid[0]="";
		//Single document insert
		/*BasicDBObject doc = new BasicDBObject();
		doc.put("address", "3-Aberdeen-Chicago-Illinois-60616");
		doc.put("price",1200);
		doc.put("ownername","Caroline Chin");
		doc.put("ownercontact",1234023456);
		doc.put("noofbedrooms",2);
		doc.put("amenities","Paid Laundry Service,free parking");
		coll.insert(doc);
		*/			
		UUID uuid;
		for (insert_asset_details=0;insert_asset_details<ASSETDETAILS_RECORDS;insert_asset_details++)
			{
			uuid=UUID.randomUUID();
			randomuuidstring=uuid.toString();
			assettype=r.nextBoolean();
			//assetid=r.nextInt(Integer.MAX_VALUE)+1;
			if (assettype==true){
				apartment_assetid[APARTMENT_RECORDS]=randomuuidstring;
				APARTMENT_RECORDS++;
				}
			else{
				independent_assetid[INDEPENDENT_RECORDS]=randomuuidstring;
				INDEPENDENT_RECORDS++;
				}
				random=r.nextInt(5);
				zipcode="";
				street="";
				ownername="";
				for (int i=0;i<6;i++)
				{
					ownername="";
					n= r.nextInt(122-97)+97;
					c=(char)n;
					street=street+Character.toString(c);
					n=r.nextInt(90-65)+65;
					c=(char)n;
					ownername=ownername+Character.toString(c);
					ownercontact=r.nextInt(999999999-100000000)+100000000;
					estgasbill= r.nextInt(450-70)+70;
					distfromdowntown=r.nextInt(25-5)+5;
					zipcode=zipcode+r.nextInt(9);
					latitude=r.nextDouble()*360-180;
					longitude=r.nextDouble()*360.0;
				}
				//System.out.println(latitude);
				//System.out.println(longitude);
				switch(random)
				{
				case 0 : city="Chicago"; state="Illinois"; country="USA";
				break;
				case 1: city="Pheonix";state="Arizona"; country="USA";
				break;
				case 2: city="Dallas";state="Texas"; country="USA";
				break;
				case 3: city="San Francisco";state="California"; country="USA";
				break;
				case 4: city="Charlotte";state="North Carolina"; country="USA";
				};
				doorno=r.nextInt(100);
				address1="";
				address2="";
				address1=doorno+"-"+street+"-"+city+"-"+zipcode;
				address2=state+"-"+country;
				noofbedrooms=r.nextInt(4-1)+1;
				maxPriceRance=r.nextInt(6000-600)+600;
				minPriceRange=maxPriceRance-500;
				preferrableBrCount=r.nextInt(4-1)+1;
				noOfParkinglots=r.nextInt(4-1)+1;
				numberOfSprinkler=r.nextInt(5-1)+1;
				section=r.nextInt(6-1)+1;
				random=r.nextInt(8);
				ageofhome=r.nextInt(100-1)+1;
				switch(random)
				{
				case 0:typeFloor="wood";
				break;
				case 1:typeFloor="bamboo";
				break;
				case 2:typeFloor="carpeted";
				break;
				case 3:typeFloor="concrete";
				break;
				case 4: typeFloor="stone";
				break;
				case 5: typeFloor="tile";
				break;
				case 6:typeFloor="laminate";
				break;
				case 7:typeFloor="cork";
				break;
				case 8:typeFloor="marble";
				break;
				default:typeFloor="bamboo";
				break;
				};
				random=r.nextInt(5);
				switch(random)
				{
				case 1:location="Chicago";
				break;
				case 2:location="Pheonix";
				break;
				case 3:location="Dallas";
				break;
				case 4:location="San Francisco";
				break;
				case 5:location="Charlotte";
				break;
				default:location="Chicago";
				break;
				}
				
				random=r.nextInt(8);
				switch(random)
				{
				case 0:suggested_amenities="gym,recreation room,centralised heating,dish washer";
				break;
				case 1:suggested_amenities="parking, laundry,indoor swimming pool,fireplace";
				break;
				case 2:suggested_amenities="laundry,gym,centralised cooling,lawn,balcony";
				break;
				case 3:suggested_amenities="recreation room, parking, outdoor swimming pool,spa";
				break;
				case 4:suggested_amenities="gym,parking,outdoor swimming pool,spa,laundry,dish washer";
				break;
				case 5:suggested_amenities="centralised heating,parking,indoor swimming pool,lawn,laundry,dish washer";
				break;
				case 6:suggested_amenities="parking,indoor swimming pool,fireplace,laundry,dish washer";
				break;
				case 7:suggested_amenities="gym,parking,outdoor swimming pool,laundry,centralised heating,dish washer";
				break;
				default:suggested_amenities="laundry,gym,centralised cooling,lawn,balcony,recreation room";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:fullyfurnished="YES";
				break;
				case 1:fullyfurnished="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:typeofroof="concrete";
				break;
				case 1:typeofroof="wood";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:hasGym="YES";
				break;
				case 1:hasGym="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:hasGarage="YES";
				break;
				case 1:hasGarage="NO";
				break;
				};
				
				random=r.nextInt(2);
				switch(random)
				{
				case 0:hasBasement="YES";
				break;
				case 1:hasBasement="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isNewHome="YES";
				break;
				case 1:isNewHome="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isFirePlaceAvailable="YES";
				break;
				case 1:isFirePlaceAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isSportsRoomAvailable="YES";
				break;
				case 1:isSportsRoomAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isCentralizedCoolingAvailable="YES";
				break;
				case 1:isCentralizedCoolingAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isRecreationRoomAvailable="YES";
				break;
				case 1:isRecreationRoomAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isDishWasherAvailable="YES";
				break;
				case 1:isDishWasherAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isCentralizedHeating="YES";
				break;
				case 1:isCentralizedHeating="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isCentralizedHeating="YES";
				break;
				case 1:isCentralizedHeating="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isLaundaryAvailable="YES";
				break;
				case 1:isLaundaryAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isbalconyAvailable="YES";
				break;
				case 1:isbalconyAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isSwimmingPoolAvailable="YES";
				break;
				case 1:isSwimmingPoolAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)	
				{
				case 0:hasClubMembership="YES";
				break;
				case 1:hasClubMembership="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:hasCentralizedHeating="YES";
				break;
				case 1:hasCentralizedHeating="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isFamilyRoomAvailable="YES";
				break;
				case 1:isFamilyRoomAvailable="NO";
				break;
				};random=r.nextInt(2);
				switch(random)
				{
				case 0:isGuestRoomAvailable="YES";
				break;
				case 1:isGuestRoomAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isDownstairsRoomAvailable="YES";
				break;
				case 1:isDownstairsRoomAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isOfficeRoomAvailable="YES";
				break;
				case 1:isOfficeRoomAvailable="NO";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:isMastersBedRoomAvailable="YES";
				break;
				case 1:isMastersBedRoomAvailable="NO";
				break;
				};
				random=r.nextInt(8);
				switch(random)
				{
				case 0:typesofrooms="workout room,library,loft,home office area";
				break;
				case 1:typesofrooms="home theatre,family room,downstairs' bedroom,basement";
				break;
				case 2:typesofrooms="guest quarters,sitting room,family room,basement,loft";
				break;
				case 3:typesofrooms="home theatre,loft, home office area, library,downstairs' bedroom";
				break;
				case 4:typesofrooms="guest quarters,sitting room,family room, library,basement";
				break;
				case 5:typesofrooms="home theatre,loft, home office area, library,basement";
				break;
				case 6:typesofrooms="library,loft, home office area, basement,family room";
				break;
				case 7:typesofrooms="sitting room,loft, home office area, library,guest quarters";
				break;
				default:typesofrooms="home theatre,loft, basement, library,family room";
				break;
				};
				random=r.nextInt(2);
				switch(random)
				{
				case 0:clubmembership="YES";
				break;
				case 1:clubmembership="NO";
				break;
				};
			coll.insert(new BasicDBObject().append("addressline1",address1).
					append("addressline2",address2).append("city",city).append("noofbedrooms",noofbedrooms).
					append("maxPriceRance",maxPriceRance).append("minPriceRange",minPriceRange).append("hasGarage",hasGarage).append("hasGym",hasGym).append("hasBasement",hasBasement).append("preferrableBrCount",preferrableBrCount).append("noOfParkinglots",noOfParkinglots).append("numberOfSprinkler",numberOfSprinkler).append("isNewHome",isNewHome).append("isFirePlaceAvailable",isFirePlaceAvailable).append("isSportsRoomAvailable",isSportsRoomAvailable).append("isCentralizedCoolingAvailable",isCentralizedCoolingAvailable).append("isRecreationRoomAvailable",isRecreationRoomAvailable).append("isDishWasherAvailable",isDishWasherAvailable).append("isCentralizedHeating",isCentralizedHeating).append("isLaundaryAvailable",isLaundaryAvailable).append("isbalconyAvailable",isbalconyAvailable).append("isSwimmingPoolAvailable",isSwimmingPoolAvailable).append("hasClubMembership",hasClubMembership).append("hasCentralizedHeating",hasCentralizedHeating).append("isFamilyRoomAvailable",isFamilyRoomAvailable).append("isGuestRoomAvailable",isGuestRoomAvailable).append("isDownstairsRoomAvailable",isDownstairsRoomAvailable).append("isOfficeRoomAvailable",isOfficeRoomAvailable).append("isMastersBedRoomAvailable",isMastersBedRoomAvailable).append("typesofrooms",typesofrooms).
					append("typeFloor",typeFloor).append("typeofroof",typeofroof).append("fullyfurnished",fullyfurnished).append("clubmembership",clubmembership).append("Estgasbill",estgasbill).append("Dist from downtown",distfromdowntown).append("ownername",ownername).append("ownercontact",ownercontact).append("ageofhome",ageofhome).append("section",section).append("latitude", latitude).append("longitude",longitude).append("assettype", assettype).append("suggested_amenities",suggested_amenities).append("location",location).append("assetid", randomuuidstring));
			}
		System.out.println(insert_asset_details +" documents were inserted in 'assetdetails' collection");
		
	for (insert_apartment=0;insert_apartment<APARTMENT_RECORDS;insert_apartment++)
	{
		random=r.nextInt(3);
		switch(random)
		{
		case 0:communityname="X";
		break;
		case 1:communityname="Y";
		break;
		case 2:communityname="Z";
		};
		random=r.nextInt(2);
		switch(random)
		{
		case 0:heater="YES";
		break;
		case 1:heater="NO";
		break;
		};
		random=r.nextInt(2);
		switch(random)
		{
		case 0:aircond="YES";
		break;
		case 1:aircond="NO";
		break;
		};
		nooffloors=r.nextInt(35-2)+2;
		whichfloor=r.nextInt(40-2)+2;
		coll1.insert(new BasicDBObject().append("communityname", communityname).append("nooffloors",nooffloors).append("whichfloor",whichfloor).append("heater",heater).append("aircond",aircond).append("assetid", apartment_assetid[insert_apartment]));
	}
	System.out.println(insert_apartment +" documents were inserted in 'apartment' collection");
		for (insert_independent=0;insert_independent<INDEPENDENT_RECORDS;insert_independent++)
		{
			random=r.nextInt(2);
			switch(random)
			{
			case 0:hasBasement="YES";
			break;
			case 1:hasBasement="NO";
			break;
			};
			random=r.nextInt(2);
			switch(random)
			{
			case 0:patio="YES";
			break;
			case 1:patio="NO";
			break;
			};
			random=r.nextInt(2);
			switch(random)
			{
			case 0:lawn="YES";
			break;
			case 1:lawn="NO";
			break;
			};
			random=r.nextInt(2);
			switch(random)
			{
			case 0:kitchenisland="YES";
			break;
			case 1:kitchenisland="NO";
			break;
			};
			random=r.nextInt(2);
			switch(random)
			{
			case 0:swimmingpool="YES";
			break;
			case 1:swimmingpool="NO";
			break;
			};
			random=r.nextInt(2);
			switch(random)
			{
			case 0:backyard="YES";
			break;
			case 1:backyard="NO";
			break;
			};
			noofcabinets=r.nextInt(3-1)+1;
		coll2.insert(new BasicDBObject().append("hasBasement",hasBasement).append("patio",patio).append("lawn",lawn).append("kitchenisland",kitchenisland).append("swimmingpool",swimmingpool).append("backyard",backyard).append("noofcabinets",noofcabinets).append("assetid", independent_assetid[insert_independent]));
		}
		System.out.println(insert_independent+" documents were inserted in 'independent' collection");
		}catch(Exception ex){		
			
		}
		}
	}
	
	
	