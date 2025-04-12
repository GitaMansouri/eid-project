package todo.service;

import todo.entity.Step;

public class StepService {
    private static Step savedStep;
        public static void saveStep(int taskRef, String title) {
           savedStep = new Step(title, taskRef);
        }
    }
