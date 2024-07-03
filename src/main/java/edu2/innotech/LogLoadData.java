package edu2.innotech;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogLoadData {
    String username;
    String f;
    String i;
    String o;
    Timestamp access_date;
    String application;
    String filename;

    public String ToFileLoadStringFormat() {
        return username + ' ' +
                f + ' ' +
                i + ' ' +
                o + ' ' +
                access_date + ' ' +
                application;
    }
}
