import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class TodoistApiTest {

    String todoistToken = "Bearer 44fb2d6a3d7ffe079a319a3c5ed7e152a135f6cb";
    String fstPrjId = "2237883857";
    String name = "";
    long newTaskId;

/*
    @Test()
    @Description("Testing projects by todoist api")
    public void getP() {
        Response response = given().
                header("Authorization", todoistToken).
                when().
                get("https://api.todoist.com/rest/v1/projects");
        response
                .then()
                .statusCode(200);
        //System.out.println("response.body() : " +response.body().prettyPeek());
        JsonPath path = response.jsonPath();
        List<Map> list = path.getList("");
        List<String> actualIDs = new ArrayList<>();
        for (Map project : list) {
            //System.out.println(project.get("id"));
            actualIDs.add((String) project.get("id").toString());

        }

        assertTrue(actualIDs.contains(fstPrjId), "Needed project id '" + fstPrjId + "' isn't found");
        //assertFalse(actualIDs.contains(fstPrjId), "Needed project id '" + fstPrjId + "' isn't found");
    }
*/
    /*
    @Test()
    @Description("Testing active tasks in Project by todoist api")
    public void getT() {
        Response response = given().
                header("Authorization", todoistToken).
                when().
                get("https://api.todoist.com/rest/v1/tasks");
        JsonPath path = response.jsonPath();
        List<Map> list = path.getList("");
        List<String> actualTAskIDs = new ArrayList<>();
        for (Map task : list) {
            if (task.get("project_id").toString().equals(fstPrjId)) {
                //System.out.println("Task Id : " + task.get("id"));
                actualTAskIDs.add((String) task.get("id").toString());
            }
        }


        System.out.println("actualTAskIDs : " + actualTAskIDs.size());
        //assertTrue(actualIDs.contains(fstPrjId), "Needed project id '" + fstPrjId + "' isn't found");
        //assertFalse(actualIDs.contains(fstPrjId), "Needed project id '" + fstPrjId + "' isn't found");
        //System.out.println(response.getBody().peek());
        assertEquals(actualTAskIDs.size(), 3, "Wrong  tasks amount. Expected '3' but found : " + actualTAskIDs.size());
    }
    */


    @Test(priority = 1)
    @Description("Create a new task")
    public void createNewTask() {
        Faker faker = new Faker();
        name = faker.name().fullName();
        System.out.println("Name :" + name );

        String reqBody = "{\n" +
                "    \"comment_count\": 0,\n" +
                "    \"completed\": false,\n" +
                "    \"content\": \"Appointment with "+name+"\",\n" +
                "    \"priority\": 4,\n" +
                "    \"project_id\": 2237883857,\n" +
                "    \"section_id\": 6789,\n" +
                "    \"url\": \"https://todoist.com/showTask?id=123\"\n" +
                "}";

        //Response response =
        newTaskId = given().
                header("Authorization", todoistToken).
                contentType(ContentType.JSON).
                //body(new File("CreateTaskBody.json")).
                body(reqBody).
                when().
                    post("https://api.todoist.com/rest/v1/tasks").
                then().
                        statusCode(200).
                        extract().
                        path("id");

    }

    //@Test(dependsOnMethods = "createNewTask")
    @Test(priority = 2)
    @Description("Get active task")
    public void getActiveTask() {
        Response response = given().
                header("Authorization", todoistToken).
                when().
                get("https://api.todoist.com/rest/v1/tasks/" + newTaskId);

        String createdTaskContent = response.body().path("content").toString();
        System.out.println(createdTaskContent);

        assertEquals("Appointment with " + name, createdTaskContent, "ERROR. Expected 'Appointment with  "+name+"' but found : " + createdTaskContent);
    }

    //@Test(dependsOnMethods = "createNewTask")
    @Test(priority = 3)
    @Description("Update task")
    public void updateNewTask() {
        String reqBody = "{\n" +
                "\t\"content\": \"NEW Appointment with  "+name+"  \"\n" +
                "}";
        //String updatedTaskContent =
        given().
                header("Authorization", todoistToken).
                contentType(ContentType.JSON).
                //body(new File("CreateTaskBody.json")).
                        body(reqBody).
        when().
                        post("https://api.todoist.com/rest/v1/tasks/" + newTaskId).
        then().
                        statusCode(204);
        //assertEquals("NEW Appointment with "+name, updatedTaskContent, "ERROR: task wasn't updated expected NEW Appointment with " + name + " but found" + updatedTaskContent);
    }

    //@Test(dependsOnMethods = "createNewTask")
    @Test(priority = 4)
    @Description("Close a task")
    public void closeTask() {
        //String updatedTaskContent =
        given().
                header("Authorization", todoistToken).
                contentType(ContentType.JSON).
                //body(new File("CreateTaskBody.json")).
        when().
                        post("https://api.todoist.com/rest/v1/tasks/"+newTaskId+"/close").
        then().
                        statusCode(204);
        //assertEquals("NEW Appointment with "+name, updatedTaskContent, "ERROR: task wasn't updated expected NEW Appointment with " + name + " but found" + updatedTaskContent);
    }

    @Test(priority = 5)
    @Description("Reopen a task")
    public void reopenTask() {
        given().
                header("Authorization", todoistToken).
        when().
                        post("https://api.todoist.com/rest/v1/tasks/"+newTaskId+"/reopen").
        then().
                        statusCode(204);
    }

@Test(priority = 6)
    @Description("Delete a task")
    public void deleteTask() {
        given().
                header("Authorization", todoistToken).
        when().
                        delete("https://api.todoist.com/rest/v1/tasks/"+newTaskId).
        then().
                        statusCode(204);
    }
}
