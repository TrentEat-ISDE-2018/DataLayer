package datalayer.dataCentric_Agritur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import datalayer.dataAdapter_Geocoding.GoogleMapsHandler;
import datalayer.dataAdapter_Geocoding.MapsResponse;
import datalayer.dataCentric_Agritur.model.AgriturEntity;

public class AgriturMapper {
	public static void saveDataInDB() throws IOException {
		FileReader fr = new FileReader("agritur_ristoranti - elenco_agritur_attivi.tsv");
		BufferedReader br = new BufferedReader(fr);
		
		br.readLine(); // header
		String currentLine = br.readLine(); 
		while(currentLine != null) {
			//split CSV line
			String[] tokens = currentLine.split("\t", -1);
			//save every data in a string
			String pref = tokens[0];
			String tel = tokens[1]; 
			String mail = tokens[2]; 
			String sito = tokens[3]; 
			String altitudine = tokens[4]; 
			String comune = tokens[5];
			String indirizzo = tokens[6]; 
			String nome = tokens[7].toUpperCase(); 
			String postiLetto = tokens[8]; 
			String postiTavola = tokens[9]; 

			AgriturEntity a = new AgriturEntity();
			//adding address
			String address = indirizzo + ", " + comune;
			a.setAddress(address);
			//adding geocoding			
			MapsResponse result = GoogleMapsHandler.sendRequest(URLEncoder.encode(address, "UTF-8"));
			a.setLat(result.getLat());
			a.setLon(result.getLon());
			//adding null or altitude
			try{
				a.setAltitude(Integer.parseInt(altitudine));
			}
			catch(NumberFormatException e) {
				a.setAltitude(null);
			}	
			//add null or mail
			a.setEmail(mail);
			if(mail.equals("")) {
				a.setEmail(null);
			}
			//add name (never null)
			a.setName(nome);
			//add num for eat
			try{
				a.setNum_for_eat(Integer.parseInt(postiTavola));
			}
			catch(NumberFormatException e) {
				a.setNum_for_eat(null);
			}
			//add num for night
			try{
				a.setNum_for_sleep(Integer.parseInt(postiLetto));
			}
			catch(NumberFormatException e) {
				a.setNum_for_sleep(null);
			}
			//add phone as string
			a.setPhone("0"+pref+" "+tel);
			//add website or null
			if(sito.equals("")) {
				a.setWebsite(null);
			}
			else {
				a.setWebsite(sito);
			}
			
			ObjectMapper mapper = new ObjectMapper();		
			String outputString = mapper.writeValueAsString(a);	
			System.out.println(outputString);
			
			AgriturEntity.saveAgritur(a);
			currentLine = br.readLine();
		}
		br.close();
	}
	
	
	public static void main(String args[]) throws IOException {
		AgriturMapper.saveDataInDB();
		
		System.out.println("saved db ended");
		AgriturEntity a = AgriturEntity.getById("AGRITUR MASO OLIVA");
		
		ObjectMapper mapper = new ObjectMapper();		
		String outputString = mapper.writeValueAsString(a);	
		System.out.println(outputString);
	}
}

