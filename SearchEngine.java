import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> queries = new ArrayList<String>();
    ArrayList<String> returnStrings = new ArrayList<String>();
    public String handleRequest(URI url) {
        if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                queries.add(parameters[1]);
            }
            return parameters[1];
        } else if (url.getPath().equals("/search")) {
            
            String[] parameters = url.getQuery().split("=");
            for(int i = 0; i < queries.size(); i++)
            {
                if(queries.get(i).contains(parameters[1]))
                {
                    returnStrings.add(queries.get(i));
                }
            }
            return returnStrings.toString();
            // for(int j = 0; j < returnStrings.size(); j++){
            //     return returnStrings.get(j);
            // }
        }
        else{
            return "404 Not Found!";
        }
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
