package swp391.quizpracticing.Utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

public class Utility {
    public static String getSiteURL() {
        UriComponents components=ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build();
        String siteUrl = components.toUriString();
        String path=components.getPath();
        return siteUrl.replace(path, "");
    }
    
    public static String checkAnswerValid(String answer){
        String[] arr = answer.split("-");
        if(arr.length > 1){
            if(arr[0].trim().equals("correct")){
                return arr[1].trim();
            }
            return "";
        }
        return "";
    }

}
