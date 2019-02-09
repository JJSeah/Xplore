package sg.edu.np.s10177744connect.madassignment.models;

import java.util.ArrayList;
import java.util.List;

public class ServiceNoModel {
    private String serviceNo;
    private String Operator;
    private List<NextBus> busList = new ArrayList<>();
    public List<NextBus> getBusList() {
        return busList;
    }

    public void setBusList(List<NextBus> busList) {
        this.busList = busList;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }



    public static class NextBus {
        private String destinationCode;
        private long estimatedArrival;
        private String latitude;
        private String longitude;
        private String visitNumber;
        private String load;
        private String feature;
        private String types;
        private String originCode;

        public NextBus(String destinationCode, long estimatedArrival, String latitude, String longitude, String visitNumber, String load, String feature, String types, String originCode) {
            this.destinationCode = destinationCode;
            this.estimatedArrival = estimatedArrival;
            this.latitude = latitude;
            this.longitude = longitude;
            this.visitNumber = visitNumber;
            this.load = load;
            this.feature = feature;
            this.types = types;
            this.originCode = originCode;
        }
        public String getOriginCode() {
            return originCode;
        }

        public void setOriginCode(String originCode) {
            this.originCode = originCode;
        }

        public String getDestinationCode() {
            return destinationCode;
        }

        public void setDestinationCode(String destinationCode) {
            this.destinationCode = destinationCode;
        }

        public long getEstimatedArrival() {
            return estimatedArrival;
        }

        public void setEstimatedArrival(long estimatedArrival) {
            this.estimatedArrival = estimatedArrival;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getVisitNumber() {
            return visitNumber;
        }

        public void setVisitNumber(String visitNumber) {
            this.visitNumber = visitNumber;
        }

        public String getLoad() {
            return load;
        }

        public void setLoad(String load) {
            this.load = load;
        }

        public String getFeature() {
            return feature;
        }

        public void setFeature(String feature) {
            this.feature = feature;
        }

        public String getTypes() {
            return types;
        }

        public void setTypes(String types) {
            this.types = types;
        }

    }
}
