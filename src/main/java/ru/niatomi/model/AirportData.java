package ru.niatomi.model;

import lombok.*;

/**
 * @author niatomi
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AirportData {

    private String key;
    private String value;

    @Override
    public String toString() {
        return  "" +  key +
                '[' + value + ']';
    }

}
