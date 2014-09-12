import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC
import java.text.SimpleDateFormat
import java.util.Date

class BuildLightProject{

   private String host
   private String port
   private String project


   public BuildLightProject(){}

   public BuildLightProject(String host, String port, String project){
      this.host = host
      this.port = port
      this.project = project.replaceAll(" ", "%20")
   }
   
   private String getProjectName() {
	return this.project.replaceAll("%20", " ")
   }

   private String getCurrentStatus() {
	try{
          
  	def restClient = new RESTClient()
        def resp = restClient.get(uri : "http://" + this.host + ":" + this.port + "/job/" + this.project + "/lastBuild/api/xml?xpath=/freeStyleBuild/result") 
	//println "The response = " + resp
	return resp.data.text()

	}
	catch(Exception e) {
           // not found
	       //println "exception is " + e
           return "SUCCESS"	  
      
        }
   }
   public String build(){
      def result = getCurrentStatus()
      return result
   }
   
   public static main(String[] arg){
     //groovy BuildLightProject "localhost" "8080" "CcdnWorkflow Smoke Test"
     def bm = new BuildLightProject(arg[0], arg[1], arg[2])
     println bm.getCurrentStatus()
   }

}

