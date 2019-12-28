package files;

public class payload {

    public static String getPostData() {

        String bodyContent = "{\n" +
                "    \"location\": {\n" +
                "        \"lat\": -38.383494,\n" +
                "        \"lng\": 33.427362\n" +
                "    },\n" +
                "    \"accuracy\": 50,\n" +
                "    \"name\": \"Frontline house\",\n" +
                "    \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "    \"address\": \"29, side layout, cohen 09\",\n" +
                "    \"types\": [\n" +
                "        \"shoe park\",\n" +
                "        \"shop\"\n" +
                "    ],\n" +
                "    \"website\": \"http://google.com\",\n" +
                "    \"language\": \"French-IN\"\n" +
                "}";
        return bodyContent;
    }

    public static String addBook(String isbnNumber, String aisleNumber) {

        String payload =
                "{\n" +
                        "   \"name\": \"Learn Appium Automation with Java\",\n" +
                        "   \"isbn\": \"" + isbnNumber + "\",\n" +
                        "   \"aisle\": \"" + aisleNumber + "\",\n" +
                        "   \"author\": \"John foe\"\n" +
                        "}";
        return payload;
    }
}
