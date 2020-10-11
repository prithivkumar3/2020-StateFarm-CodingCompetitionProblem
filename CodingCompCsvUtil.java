package sf.codingcompetition2020;

import java.io.*;  
import java.util.Scanner; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import sf.codingcompetition2020.structures.Agent;
import sf.codingcompetition2020.structures.Claim;
import sf.codingcompetition2020.structures.Customer;
import sf.codingcompetition2020.structures.Vendor;

public class CodingCompCsvUtil {
	
	/* #1 
	 * readCsvFile() -- Read in a CSV File and return a list of entries in that file.
	 * @param filePath -- Path to file being read in.
	 * @param classType -- Class of entries being read in.
	 * @return -- List of entries being returned.
	 */
	public <T> List<T> readCsvFile(String filePath, Class<T> classType) {
		List<T> readCsv = new ArrayList<T>();
		//parsing a CSV file into Scanner class constructor  
		Scanner sc;
		int count = 0;
		int paramCt = 0;
		int type = 0;
		int paramLength = 0;
	
		ArrayList<String[]> params = new ArrayList<String[]>();
		try {
			sc = new Scanner(new File(filePath));
			sc.useDelimiter("\n");   //sets the delimiter pattern  
			while (sc.hasNext()) { //returns a boolean value
				if (count != 0) {
					
					if (paramCt == 0) {
						String ne = sc.next().toString();
						String[] temp = ne.split(",");
						switch(temp.length) {
						case 5 :  
							paramLength = 5;
							break;
						case 4 :
							type = 1;
							paramLength = 4;
							break;
						case 15 :
							type = 2;
							paramLength = 15;
							break;
						default :
							paramLength = 5;
							break;
						}
						paramCt++;
					} else {
						String nex = sc.next().toString();
						params.add(nex.split(","));
					}
				}
				count++;
			}
			for (int i = 0; i < params.size(); i++) {
				switch(type) {
				case 0 : 
					readCsv.add((T)new Agent(Integer.parseInt(params.get(i)[0]), params.get(i)[1], params.get(i)[2], params.get(i)[3], params.get(i)[4]));
					break;
				case 1 :
					readCsv.add((T)new Claim(Integer.parseInt(params.get(i)[0]), Integer.parseInt(params.get(i)[1]), Boolean.parseBoolean(params.get(i)[2]), Integer.parseInt(params.get(i)[3])));
					break;
				case 2 :
					readCsv.add((T)new Customer(Integer.parseInt(params.get(i)[0]), params.get(i)[1], params.get(i)[2], Integer.parseInt(params.get(i)[3]), params.get(i)[4], Integer.parseInt(params.get(i)[5]), Short.parseShort(params.get(i)[6]), params.get(i)[7], new List<Dependent>()/*(ArrayList<>)(params.get(i)[8].split(","))*/, Boolean.parseBoolean(params.get(i)[9]), Boolean.parseBoolean(params.get(i)[10]), Boolean.parseBoolean(params.get(i)[11]), params.get(i)[12], Short.parseShort(params.get(i)[13]), (Integer)Integer.parseInt(params.get(i)[14])));
					break;
				default :
					break;
				}
			}
			sc.close();  //closes the scanner 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  

		return readCsv;
	}

	
	/* #2
	 * getAgentCountInArea() -- Return the number of agents in a given area.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @return -- The number of agents in a given area
	 */
	public int getAgentCountInArea(String filePath,String area) {

	}

	
	/* #3
	 * getAgentsInAreaThatSpeakLanguage() -- Return a list of agents from a given area, that speak a certain language.
	 * @param filePath -- Path to file being read in.
	 * @param area -- The area from which the agents should be counted.
	 * @param language -- The language spoken by the agent(s).
	 * @return -- The number of agents in a given area
	 */
	public List<Agent> getAgentsInAreaThatSpeakLanguage(String filePath, String area, String language) {

	}
	
	
	/* #4
	 * countCustomersFromAreaThatUseAgent() -- Return the number of individuals from an area that use a certain agent.
	 * @param filePath -- Path to file being read in.
	 * @param customerArea -- The area from which the customers should be counted.
	 * @param agentFirstName -- First name of agent.
	 * @param agentLastName -- Last name of agent.
	 * @return -- The number of customers that use a certain agent in a given area.
	 */
	public short countCustomersFromAreaThatUseAgent(Map<String,String> csvFilePaths, String customerArea, String agentFirstName, String agentLastName) {
		
	}

	
	/* #5
	 * getCustomersRetainedForYearsByPlcyCostAsc() -- Return a list of customers retained for a given number of years, in ascending order of their policy cost.
	 * @param filePath -- Path to file being read in.
	 * @param yearsOfServeice -- Number of years the person has been a customer.
	 * @return -- List of customers retained for a given number of years, in ascending order of policy cost.
	 */
	public List<Customer> getCustomersRetainedForYearsByPlcyCostAsc(String customerFilePath, short yearsOfService) {

	}

	
	/* #6
	 * getLeadsForInsurance() -- Return a list of individuals who’ve made an inquiry for a policy but have not signed up.
	 * *HINT* -- Look for customers that currently have no policies with the insurance company.
	 * @param filePath -- Path to file being read in.
	 * @return -- List of customers who’ve made an inquiry for a policy but have not signed up.
	 */
	public List<Customer> getLeadsForInsurance(String filePath) {

	}


	/* #7
	 * getVendorsWithGivenRatingThatAreInScope() -- Return a list of vendors within an area and include options to narrow it down by: 
			a.	Vendor rating
			b.	Whether that vendor is in scope of the insurance (if inScope == false, return all vendors in OR out of scope, if inScope == true, return ONLY vendors in scope)
	 * @param filePath -- Path to file being read in.
	 * @param area -- Area of the vendor.
	 * @param inScope -- Whether or not the vendor is in scope of the insurance.
	 * @param vendorRating -- The rating of the vendor.
	 * @return -- List of vendors within a given area, filtered by scope and vendor rating.
	 */
	public List<Vendor> getVendorsWithGivenRatingThatAreInScope(String filePath, String area, boolean inScope, int vendorRating) {

	}


	/* #8
	 * getUndisclosedDrivers() -- Return a list of customers between the age of 40 and 50 years (inclusive), who have:
			a.	More than X cars
			b.	less than or equal to X number of dependents.
	 * @param filePath -- Path to file being read in.
	 * @param vehiclesInsured -- The number of vehicles insured.
	 * @param dependents -- The number of dependents on the insurance policy.
	 * @return -- List of customers filtered by age, number of vehicles insured and the number of dependents.
	 */
	public List<Customer> getUndisclosedDrivers(String filePath, int vehiclesInsured, int dependents) {

	}	


	/* #9
	 * getAgentIdGivenRank() -- Return the agent with the given rank based on average customer satisfaction rating. 
	 * *HINT* -- Rating is calculated by taking all the agent rating by customers (1-5 scale) and dividing by the total number 
	 * of reviews for the agent.
	 * @param filePath -- Path to file being read in.
	 * @param agentRank -- The rank of the agent being requested.
	 * @return -- Agent ID of agent with the given rank.
	 */
	public int getAgentIdGivenRank(String filePath, int agentRank) {
			
	}	

	
	/* #10
	 * getCustomersWithClaims() -- Return a list of customers who’ve filed a claim within the last <numberOfMonths> (inclusive). 
	 * @param filePath -- Path to file being read in.
	 * @param monthsOpen -- Number of months a policy has been open.
	 * @return -- List of customers who’ve filed a claim within the last <numberOfMonths>.
	 */
	public List<Customer> getCustomersWithClaims(Map<String,String> csvFilePaths, short monthsOpen) {

	}	

}
