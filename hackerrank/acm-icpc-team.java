import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

// Brute-force solution, runs in O(n^3) time, and has O(n) storage complexity
public class Solution {
  static class Result {
    int topics;
    int teams;
    Result(int topics, int teams) {
      this.topics = topics;
      this.teams = teams;
    }
  }

  private static int mergeAndCountTopics(String a, String b) {
    int cnt = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == '1' || b.charAt(i) == '1') {
        cnt++;
      }
    }
    return cnt;
  }

  private static Result findMaxTopics(String[] topic) {
    if (topic == null || topic.length == 0) return new Result(0, 0);
    HashMap<String, Integer> agg = new HashMap<String, Integer>();
    for (int i = 0; i < topic.length; i++) {
      for (int j = i + 1; j < topic.length; j++) {
        agg.put(i + "-" + j, mergeAndCountTopics(topic[i], topic[j]));
      }
    }

    int maxTopic = 0;
    for (String key : agg.keySet()) {
      maxTopic = Math.max(maxTopic, agg.get(key));
    }

    int maxTeams = 0;
    for (String key : agg.keySet()) {
      if (agg.get(key) == maxTopic) {
        maxTeams++;
      }
    }

    return new Result(maxTopic, maxTeams);
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    String topic[] = new String[n];
    for (int topic_i = 0; topic_i < n; topic_i++) {
      topic[topic_i] = in.next();
    }

    Result res = findMaxTopics(topic);
    System.out.println(res.topics);
    System.out.println(res.teams);
  }
}
