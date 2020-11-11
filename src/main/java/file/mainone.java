package file;


import java.util.List;
import java.util.Map;

import org.patriques.*;
import org.patriques.input.timeseries.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.timeseries.Daily;
import org.patriques.output.timeseries.IntraDay;
import org.patriques.output.timeseries.Monthly;
import org.patriques.output.timeseries.Weekly;
import org.patriques.output.timeseries.data.StockData;


public class mainone {

	//Define AlphaVantage connection
	int j = 0;
	String apiKey = "Q2029FIP74HG6LYF";
    int timeout = 3000;
    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
    
    
	public static void main(String[] args) {
	    String apiKey = "Q2029FIP74HG6LYF";
	    int timeout = 5000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);

	    
	}
	
	public IntraDay oneMinute(String ticker) {
		IntraDay response = stockTimeSeries.intraDay(ticker, Interval.ONE_MIN, OutputSize.COMPACT);
	    return response;
	}
	
	public IntraDay fiveMinutes(String ticker) {
		IntraDay response = stockTimeSeries.intraDay(ticker, Interval.FIVE_MIN, OutputSize.COMPACT);
	    return response;
	}
	
	public IntraDay fifteenMinutes(String ticker) {
		IntraDay response = stockTimeSeries.intraDay(ticker, Interval.FIFTEEN_MIN, OutputSize.COMPACT);
	    return response;
	}
	
	public IntraDay thirtyMinutes(String ticker) {
		IntraDay response = stockTimeSeries.intraDay(ticker, Interval.THIRTY_MIN, OutputSize.COMPACT);
	    return response;
	}
	
	public IntraDay oneHour(String ticker) {
		IntraDay response = stockTimeSeries.intraDay(ticker, Interval.SIXTY_MIN, OutputSize.COMPACT);
	    return response;
	}
	
	public Daily oneDay(String ticker) {
		Daily response = stockTimeSeries.daily(ticker, OutputSize.COMPACT);
	    return response;
	}
	
	public Weekly oneWeek(String ticker) {
		Weekly response = stockTimeSeries.weekly(ticker);
	    return response;
	}
	
	public Monthly oneMonth(String ticker) {
		Monthly response = stockTimeSeries.monthly(ticker);
	    return response;
	}
	

	
	public int printer(int prev) {
		j = prev + 4;
		System.out.println(j);
		return j;
	}
}


