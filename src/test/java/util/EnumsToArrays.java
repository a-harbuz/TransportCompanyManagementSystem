package util;

import de.telran.transportcompanymanagementsystem.entity.enums.ServiceType;
import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class EnumsToArrays {
    public static String[] getArrayOfServiceType() {
        return Arrays.stream(ServiceType.values())
                .map(Enum::toString)
                .toArray(String[]::new);
    }
}
