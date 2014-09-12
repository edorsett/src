@Grab(group =
        'org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.6')

import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC
import java.text.SimpleDateFormat
import java.util.Date



class BuildLightManager {

    public BuildLightManager() {
    }

    public void manage(String host,
            String port) {
        String buildLightColor = null;
        String uiColor = null;
        while(true) {
    String status = getCurrentStatus(host, port)
      ///println("status --> " + status)
      if (status.contains("/red")) {
            uiColor = "RED"
        } else {
            uiColor = "GREEN"
        }

        if (uiColor == "RED" && buildLightColor != "RED") {
            println "UI color is " + uiColor + " Build LightColor is " + buildLightColor 
            //allOff
            ["USBCMDAP", "v", "0","0", "101", "12",  "0", "7"].execute() 
            //RedOn
          sleep(100)
          ["USBCMDAP", "v", "0","0", "101", "12",  "2", "0"].execute()
            buildLightColor = "RED"
        }
        if (uiColor == "GREEN" && buildLightColor != "GREEN") {
                println "UI color is " + uiColor + " Build Light color is " + buildLightColor
                 //allOff
               ["USBCMDAP", "v", "0","0", "101", "12",  "0", "7"].execute()
                sleep(100) 
                //GreeOn
              ["USBCMDAP", "v", "0","0", "101", "12",  "1", "0"].execute()
                buildLightColor = "GREEN"

            }    

            sleep(10000)
    }
    }

    private String getCurrentStatus(String host, String port) {
        try {
            def restClient = new RESTClient()
            //println "about to call"
            def resp = restClient.get(uri : "http://" + host + ":" + port + "/view/AMF%20-%20Info%20Radiator/api/xml") 
            //println "The response = " + resp.data.text()
            return resp.data.text()

        } catch (Exception e) {
            // not found
            println "exception is " + e
            return "red"   
        }
    }

    public static main(String[] arg) {
        // "localhost" "8080" "./BuildLightProjects.txt"
        //println("first arg - " + arg[0])
        //println("second arg - " + arg[1])
        def manager = new BuildLightManager()
     manager.manage(arg[0], arg[1])

    }
}
