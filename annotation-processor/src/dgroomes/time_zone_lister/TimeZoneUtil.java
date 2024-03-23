package dgroomes.time_zone_lister;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TimeZone;

public class TimeZoneUtil {
    public static List<TimeZone> findTimeZones() {
        String[] timeZoneIds = TimeZone.getAvailableIDs();
        return Arrays.stream(timeZoneIds)
                .map(TimeZone::getTimeZone)
                .sorted(Comparator.comparing(TimeZone::getRawOffset).thenComparing(TimeZone::getID))
                .toList();
    }
}
