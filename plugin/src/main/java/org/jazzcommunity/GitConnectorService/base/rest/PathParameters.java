package org.jazzcommunity.GitConnectorService.base.rest;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathParameters {
    public PathParameters(String path, String url) {
        // first, I want the names...
        // This I can probably do just once..., maybe when
        // creating the service. But I'm not going to optimize
        // this until later.
        ArrayList<String> names = getNames(path);

        System.out.println(String.format("names: %s", Joiner.on(',').join(names)));

        // this is what I need to do, to actually get the values
        String regex = path.replaceAll("\\{[^\\/]+\\}", "([^\\\\/]+)");
        System.out.println(String.format("regex: %s", regex));
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        ArrayList<String> values = new ArrayList<>();
        while(matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i += 1) {
                values.add(matcher.group(i));
            }
        }

        System.out.println(String.format("values: %s", Joiner.on(',').join(values)));
    }

    private static ArrayList<String> getNames(String path) {
        String regex = "\\{([^\\/]+)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(path);

        ArrayList<String> names = new ArrayList<>();
        while (matcher.find()) {
            names.add(matcher.group(1));
        }

        return names;
    }
}
