import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scene[] arr = new Scene[6];
        arr[0] = new Scene(SceneSpecification.YELLOW, Event.of(LocalTime.of(16, 0), LocalTime.of(22, 20), "Iron Maiden", 130));
        arr[1] = new Scene(SceneSpecification.PINK, Event.of(LocalTime.of(17, 0), LocalTime.of(19, 50), "Judas Priest", 85.7));
        arr[2] = new Scene(SceneSpecification.ORANGE, Event.of(LocalTime.of(18, 50), LocalTime.of(20, 20), "Metallica", 110));
        arr[3] = new Scene(SceneSpecification.BLACK, Event.of(LocalTime.of(19, 30), LocalTime.of(22, 20), "AC-DC", 116));
        arr[4] = new Scene(SceneSpecification.GREEN, Event.of(LocalTime.of(20, 50), LocalTime.of(22, 30), "RHCP", 99));
        arr[5] = new Scene(SceneSpecification.PURPLE, Event.of(LocalTime.of(17, 0), LocalTime.of(19, 30), "Guns N` Roses", 78.5));


        final int NUM_SCENES = 6;
        int[][] adjMat = new int[NUM_SCENES][NUM_SCENES];
        LocationMap locationMap = new LocationMap(adjMat, NUM_SCENES);

        locationMap.addRoad(SceneSpecification.YELLOW.getId(), SceneSpecification.PINK.getId(), 2);
        locationMap.addRoad(SceneSpecification.PINK.getId(), SceneSpecification.ORANGE.getId(), 2);
        locationMap.addRoad(SceneSpecification.ORANGE.getId(), SceneSpecification.BLACK.getId(), 3);
        locationMap.addRoad(SceneSpecification.BLACK.getId(), SceneSpecification.GREEN.getId(), 2);
        locationMap.addRoad(SceneSpecification.GREEN.getId(), SceneSpecification.PURPLE.getId(), 2);
        locationMap.addRoad(SceneSpecification.PURPLE.getId(), SceneSpecification.YELLOW.getId(), 3);
        locationMap.addRoad(SceneSpecification.YELLOW.getId(), SceneSpecification.GREEN.getId(), 6);
        locationMap.addRoad(SceneSpecification.PURPLE.getId(), SceneSpecification.GREEN.getId(), 3);
        locationMap.addRoad(SceneSpecification.ORANGE.getId(), SceneSpecification.GREEN.getId(), 6);

        double distance = 0;
        double allPrice = 0;
        ArrayList<Scene> arrayList = createConcertPlan(arr);

        for (int index = 0; index < arrayList.size(); index++) {

            int[] roads = shortestPathBetweenScenes(locationMap, arrayList.get(index).getSceneSpecification().getId());

            if (index < arrayList.size() - 1) {
                int idOfScene = arrayList.get(index + 1).getSceneSpecification().getId();
                distance += roads[idOfScene];
            }

            allPrice += arrayList.get(index).getEvent().getPrice();
            System.out.println(arrayList.get(index) + " " + arrayList.get(index).getEvent().getDuration().toMinutes() + " min ***");
        }

        System.out.println("price for all events: " + allPrice + "$");
        System.out.println("price for transfer : " + distance * EventUtil.PRICE_FOR_KM + "$");
        System.out.println("total price : " + (allPrice + (distance * EventUtil.PRICE_FOR_KM)) + "$");

    }


    public static ArrayList<Scene> createConcertPlan(Scene[] scenes) {
        int currentScene, next;
        Arrays.sort(scenes, Comparator.comparing(x -> x.getEvent().getFinish()));
        currentScene = 0;
        ArrayList<Scene> res = new ArrayList<>();
        res.add(scenes[currentScene]);
        for (next = 1; next < scenes.length; next++) {
            if (Duration.between(scenes[currentScene].getEvent().getFinish(), scenes[next].getEvent().getStart()).toMinutes() > 0) {
                res.add(scenes[next]);
                currentScene = next;
            }
        }
        return res;
    }

    public static int[] shortestPathBetweenScenes(LocationMap locationMap, int src) {
        int[] distance = new int[locationMap.getNumOfScenes()];
        //initial distance is infinite
        Arrays.fill(distance, Integer.MAX_VALUE);
        boolean[] visited = new boolean[locationMap.getNumOfScenes()];
        Arrays.fill(visited, false);

        distance[src - 1] = 0;
        for (int i = 0; i < locationMap.getNumOfScenes(); i++) {
            int closestVertex = getClosestLocation(distance, visited);
            visited[closestVertex] = true;
            for (int j = 0; j < locationMap.getNumOfScenes(); j++) {
                if (!visited[j]) {
                    if (locationMap.getAdjointMatrix()[closestVertex][j] != 0) {
                        int d = distance[closestVertex] + locationMap.getAdjointMatrix()[closestVertex][j];
                        if (d < distance[j])
                            distance[j] = d;
                    }
                }
            }
        }
        return distance;
    }

    public static int getClosestLocation(int[] distance, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIdx = -1;

        for (int i = 0; i < distance.length; i++) {
            if (distance[i] < min)
                if (!visited[i]) {
                    min = distance[i];
                    minIdx = i;
                }
        }

        return minIdx;
    }

}