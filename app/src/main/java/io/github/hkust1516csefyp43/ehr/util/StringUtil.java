package io.github.hkust1516csefyp43.ehr.util;

import io.github.hkust1516csefyp43.ehr.pojo.Person;

/**
 * Utils related to String
 * Created by Louis on 6/10/15.
 */
public class StringUtil {

    public String getTextDrawableText(Person p) {
        if (p != null) {
            if (p.last_name == null) {
                //just firstname
                if (p.first_name == null) {
                    //no name
                    return "?";
                }
                return "f";
            } else if (p.first_name == null) {
                //just lastname
                return "l";
            } else {
                //normal
                return "fl";
            }
        } else
            return "?";
    }

}
