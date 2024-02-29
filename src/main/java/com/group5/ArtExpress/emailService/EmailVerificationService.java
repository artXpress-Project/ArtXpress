package com.group5.ArtExpress.emailService;

import com.group5.ArtExpress.customException.EmailAlreadyExistException;
import com.group5.ArtExpress.repository.CollectorRepo;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@Service
@Validated
public class EmailVerificationService {
    @Autowired
    CollectorRepo collectorRepo;
    public boolean verifyEmailFormat(@Email String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void ifEmailAlreadyExist(String email){
        if (collectorRepo.existsByEmail(email)){
           throw new EmailAlreadyExistException("Email already exist");
        }

    }

//    public boolean verifyEmailDomain(String email) {
//        String[] parts = email.split("@");
//        String domain = parts[1];
//
//        try {
//            Properties env = new Properties();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
//            DirContext ctx = new InitialDirContext(env);
//
//            SearchControls controls = new SearchControls();
//            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
//            controls.setTimeLimit(5000);
//
//            NamingEnumeration<SearchResult> results = ctx.search("dns:/"+domain, "(objectClass=MX)", controls);
//            boolean hasMXRecords = results.hasMore();
//            ctx.close();
//
//            return hasMXRecords;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//    public boolean verifyEmail(@Valid String email) {
//        return verifyEmailFormat(email) && verifyEmailDomain(email);
//    }
//
}
