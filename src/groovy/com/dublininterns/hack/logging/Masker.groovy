package com.dublininterns.hack.logging

import org.apache.commons.lang.StringUtils

import java.util.regex.Matcher
import java.util.regex.Pattern

class Masker {
    private static final int CENSOR_LENGTH = 10
    private static final String CENSOR_POSTFIX = "..."
    private static final String IDENTIFIER_PATTERN = "['\"]([^'\"]+)['\"]"
    private static final def ACCESS_ELEMENT_PATTERNS = [Pattern.compile("[Pp]hone[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"), Pattern.compile("[Pp]hone[Nn]umber[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"), Pattern.compile("[Uu]ser[Nn]ame[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"), Pattern.compile("[Nn]ame[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"),Pattern.compile("[Ss]ession[Ii]d[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"), Pattern.compile("[Cc]art[Aa]ccess[Tt]oken[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"), Pattern.compile("[Tt]ickle[Ss]ession[\"|']?[:=][\"|']?${IDENTIFIER_PATTERN}"), Pattern.compile( "[:=] ?[\"|']?([a-zA-Z0-9._%+-]*)@([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})[\"|']?" )]
    private static final def ARGUMENTS_TO_REPLACE = ["[Pp]assword", "[Pp]an", "[Ee]xpiry", "[Cc]vc2", "[Pp]assword", "[Pp]hone", "[Pp]hone[Nn]umber", "secret"]

    private static final int DEFAULT_MASK_REVEAL_LENGTH = 4

    public static String mask( String variable ) {

        if ( StringUtils.isBlank( variable ) ) {

            return "*"

        }

        StringUtils.repeat( "*", variable ? variable.length() : 1 )

    }

    public static String maskFirstThird( String variable) {
        // no harm being defensive
        if (variable != null) {
            def length = variable.length();
            int one_third = length / 3;
            return mask(variable, length - one_third)
        } else {
            return variable;
        }
    }

    public static String mask( String variable, int remaining ) {

        String masked = mask( variable )

        int lengthToReveal

        if ( remaining > 0 ) {

            lengthToReveal = remaining

        } else {

            lengthToReveal = DEFAULT_MASK_REVEAL_LENGTH;

        }

        lengthToReveal = variable && variable.length() > lengthToReveal ? lengthToReveal : DEFAULT_MASK_REVEAL_LENGTH
        lengthToReveal = variable && variable.length() > lengthToReveal ? lengthToReveal : 0

        if ( lengthToReveal > 0 ) {

            "${masked.substring(0, masked.length() - lengthToReveal)}${variable.substring( variable.length() - lengthToReveal )}"

        } else {

            masked

        }

    }

    public static String maskSmartly( String message ) {

        if ( !message ) {

            return message

        }

        for(String r : ARGUMENTS_TO_REPLACE ) {
            String from = r + "['\"]?[:=]['\"]?[^'\"]+['\"]?";
            String to = r + "='****'";
            message = message.replaceAll(from, to);
        }

        return censorAccessElements(message);
    }



    private static String censorAccessElements(String reqStr) {

        String censoredStr = reqStr
        ACCESS_ELEMENT_PATTERNS.each {
            Matcher m = it.matcher(censoredStr)
            StringBuffer buffer = new StringBuffer();
            while (m.find()) {
                String matchedValue = m.group(1)
                String substituteValue = StringUtils.left(matchedValue, CENSOR_LENGTH) + CENSOR_POSTFIX
                m.appendReplacement(buffer, m.group().replace(matchedValue, substituteValue))
            }
            m.appendTail(buffer)
            censoredStr = buffer.toString()
        }
        return censoredStr
    }

}
