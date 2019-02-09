package sg.edu.np.s10177744connect.madassignment;

import android.os.AsyncTask;

import sg.edu.np.s10177744connect.madassignment.models.ServiceNoModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class fetchData extends AsyncTask<Void, Void, Void> {
    StringBuffer data = new StringBuffer();
    String uri = "http://datamall2.mytransport.sg/";
    String path = "ltaodataservice/BusArrivalv2?";
    String busStop = MainActivity.busStop.getText().toString();
    static List<ServiceNoModel> ServiceNoList = new ArrayList<>();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    Date now = new Date();
    @Override
    protected Void doInBackground(Void... voids) {
        if (ServiceNoList != null); {
            ServiceNoList.clear();
        }
        try {
            URL url = new URL(uri + path + "BusStopCode=" + busStop);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("AccountKey", "dXTg22JORJitgUmddjUPKA==");
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null) {
                line = bufferedReader.readLine();
                data = data.append(line);
            }

            JSONObject obj_JSONObject = new JSONObject(data.toString());
            JSONArray obj_JSONArray = obj_JSONObject.getJSONArray("Services");


            //Parsing data from JSON and inputting into the list to be used
            for(int i=0; i<obj_JSONArray.length(); i++) {
                JSONObject serviceObject = obj_JSONArray.getJSONObject(i);
                ServiceNoModel serviceNoModel = new ServiceNoModel();
                serviceNoModel.setServiceNo(serviceObject.getString("ServiceNo"));
                serviceNoModel.setOperator(serviceObject.getString("Operator"));

                JSONObject nextBus = serviceObject.getJSONObject("NextBus");
                JSONObject nextBus2 = serviceObject.getJSONObject("NextBus2");
                JSONObject nextBus3 = serviceObject.getJSONObject("NextBus3");

                String nextBusTimeString = nextBus.getString("EstimatedArrival");
                Date nextBusTimingDate = format.parse(nextBusTimeString);
                long nextBusTiming = nextBusTimingDate.getTime() - now.getTime();
                nextBusTiming = nextBusTiming/60000;

                String nextBus2TimeString = nextBus2.getString("EstimatedArrival");
                Date nextBus2TimingDate = format.parse(nextBus2TimeString);
                long nextBus2Timing = nextBus2TimingDate.getTime() - now.getTime();
                nextBus2Timing = nextBus2Timing/60000;

                String nextBus3TimeString = nextBus3.getString("EstimatedArrival");
                Date nextBus3TimingDate = format.parse(nextBus3TimeString);
                long nextBus3Timing = nextBus3TimingDate.getTime() - now.getTime();
                nextBus3Timing = nextBus3Timing/60000;

                ServiceNoModel.NextBus incomingBus = new ServiceNoModel.NextBus(nextBus.getString("DestinationCode"),nextBusTiming,nextBus.getString("Latitude"),nextBus.getString("Longitude"),nextBus.getString("VisitNumber"), nextBus.getString("Load"),nextBus.getString("Feature"),nextBus.getString("Type"),nextBus.getString("OriginCode"));
                ServiceNoModel.NextBus incomingBus2 = new ServiceNoModel.NextBus(nextBus2.getString("DestinationCode"),nextBus2Timing,nextBus2.getString("Latitude"),nextBus2.getString("Longitude"),nextBus2.getString("VisitNumber"), nextBus2.getString("Load"),nextBus2.getString("Feature"),nextBus2.getString("Type"),nextBus2.getString("OriginCode"));
                ServiceNoModel.NextBus incomingBus3 = new ServiceNoModel.NextBus(nextBus3.getString("DestinationCode"),nextBus3Timing,nextBus3.getString("Latitude"),nextBus3.getString("Longitude"),nextBus3.getString("VisitNumber"), nextBus3.getString("Load"),nextBus3.getString("Feature"),nextBus3.getString("Type"),nextBus3.getString("OriginCode"));
                serviceNoModel.getBusList().add(incomingBus);
                serviceNoModel.getBusList().add(incomingBus2);
                serviceNoModel.getBusList().add(incomingBus3);
                ServiceNoList.add(serviceNoModel);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
        MainActivity.bAdapter.notifyDataSetChanged();
    }
}
