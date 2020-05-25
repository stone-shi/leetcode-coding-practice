package com.shifamily.dev.leetcode.practice.string;

import com.shifamily.dev.BasicStudy;
import com.shifamily.dev.CaseRunner;

import java.util.HashSet;
import java.util.Set;

/*
929. Unique Email Addresses
Easy
Every email consists of a local name and a domain name, separated by the @ sign.

For example, in alice@leetcode.com, alice is the local name, and leetcode.com is the domain name.

Besides lowercase letters, these emails may contain '.'s or '+'s.

If you add periods ('.') between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus ('+') in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered, for example m.y+name@email.com will be forwarded to my@email.com.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of emails, we send one email to each address in the list.  How many different addresses actually receive mails?



Example 1:

Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails


Note:

1 <= emails[i].length <= 100
1 <= emails.length <= 100
Each emails[i] contains exactly one '@' character.
All local and domain names are non-empty.
Local names do not start with a '+' character.
 */
public class Leet929UniqueEmailAddresses extends BasicStudy {

    public Leet929UniqueEmailAddresses() {
        String[][] casesP1 = {{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}};
        int[] answers = {2};

        for (int i = 0; i < casesP1.length; i++) {
            Object[] p = new Object[1];
            p[0] = casesP1[i];
            addParameterAndAnswer(p, answers[i], false);
        }

    }


    @CaseRunner
    public int numUniqueEmails(String[] emails) {
        Set<String> uniqueEmail = new HashSet<>();
        for (String email: emails){
            char[] cs = email.toCharArray();
            StringBuilder sb = new StringBuilder(cs.length);
            boolean plus = false;
            boolean at = false;
            for (char c : cs){
                if (c == '@') {
                    plus = false;
                    at = true;
                }
                if (c == '.' && !at)
                    continue;

                if (c == '+')
                    plus = true;


                if (!plus)
                    sb.append(c);

            }
            uniqueEmail.add(sb.toString());
        }
        return uniqueEmail.size();

    }
}
