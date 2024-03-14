package com.group5.ArtExpress.emailService;

import com.group5.ArtExpress.customException.CouldNotFindEmailException;
import com.group5.ArtExpress.customException.EmailAlreadyExistException;
import com.group5.ArtExpress.customException.LockException;
import com.group5.ArtExpress.data.models.Artist;
import com.group5.ArtExpress.data.models.Collector;
import com.group5.ArtExpress.data.models.ExhibitionEventRegistration;
import com.group5.ArtExpress.dto.responseDto.MessageResponse;
import com.group5.ArtExpress.repository.ArtistRepo;
import com.group5.ArtExpress.repository.CollectorRepo;
import com.group5.ArtExpress.repository.ExhibitionEventRepo;
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
     @Autowired
    ArtistRepo artistRepo;

     @Autowired
    ExhibitionEventRepo exhibitionEventRepo;
    public boolean verifyEmailFormat(@Email String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void ifArtistEmailAlreadyExist(String email){
        if (artistRepo.existsByEmail(email)) throw new EmailAlreadyExistException("Email already exist");
    }

    public void ifCollectorEmailAlreadyExist(String email){
       if(collectorRepo.existsByEmail(email)) throw new EmailAlreadyExistException("Email already exist");
    }

    public void ifExhibitionUserEmailAlreadyExist(String email){
        if (collectorRepo.existsByEmail(email)) throw new EmailAlreadyExistException("Email already exist");
    }

    public Artist findArtistEmail(String email){
        Artist artist = artistRepo.findByEmailIgnoreCase(email);
        if(artist == null) throw new CouldNotFindEmailException("Could not find user with this particular" + email);
        else return artist;
    }

    public Collector findCollectorEmail(String email){
        Collector collector = collectorRepo.findByEmailIgnoreCase(email);
        if(collector == null) throw new CouldNotFindEmailException("Could not find user with this particular" + email);
        else return collector;
    }

    public ExhibitionEventRegistration findExhibitionEmail(String email){
        ExhibitionEventRegistration eventRegistration = exhibitionEventRepo.findByEmailIgnoreCase(email);
        if(eventRegistration == null) throw new CouldNotFindEmailException("Could not find user with this particular" + email);
        else return eventRegistration;
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
