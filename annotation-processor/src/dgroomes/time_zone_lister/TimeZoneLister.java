package dgroomes.time_zone_lister;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

import static java.lang.System.out;

/**
 * Please see the README for more information.
 */
public class TimeZoneLister {

    public static void main(String[] args) {
        List<TimeZone> timeZones = findTimeZones();
        out.println("The following time zones were found:");
        for (TimeZone timeZone : timeZones) {
            out.printf("\t%s%n", timeZone.getID());
        }
    }

    private static List<TimeZone> findTimeZones() {
        String[] timeZoneIds = TimeZone.getAvailableIDs();
        return Arrays.stream(timeZoneIds)
                .map(TimeZone::getTimeZone)
                .sorted(Comparator.comparing(TimeZone::getRawOffset).thenComparing(TimeZone::getID))
                .toList();
    }
}
