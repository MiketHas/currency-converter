package main.java;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Currencies {
    static double eur;
    static double usd;
    static double hkd;
    static double gbp;

    public static void setCurrentExchangeRate() {
        usd = getRate("USD");
        eur = getRate("EUR");
        gbp = getRate("GBP");
        hkd = getRate("HKD");
    }

    public static Double getRate(String currency) {
        String curr = getQuote(currency);
        double d=Double.parseDouble(curr.replace(",", "."));
        return d;
    }

    public static String getContent(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse response = client.send(
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(url))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            return (String) response.body();
        } catch (Exception e) {
            return "Opps!";
        }
    }

    private static String getPreciseQuote(String content){
        int indexOfSpan = content.indexOf("<td class=\"right\">");
        int indexOfSpanClosed = content.indexOf("</td>", indexOfSpan);
        int spanLength = "<td class=\"right\">".length();
        return content.substring(indexOfSpan + spanLength, indexOfSpanClosed);
    }

    private static String getContent(String content, String discriminator) {
        int indexOfTitle = content.indexOf("\"right\">" + "1 " + discriminator + "</td>"); // index of right>
        content = content.substring(indexOfTitle);
        return content;
    }

    public static String getQuote(String currency) {
        String content = getContent("https://www.nbp.pl/home.aspx?f=/kursy/kursya.html");

        content = getContent(content, currency);
        return getPreciseQuote(content);
    }
}
