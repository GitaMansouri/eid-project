import db.Database;
import db.Entity;
import db.exception.EntityNotFoundException;
import db.exception.InvalidEntityException;
import todo.entity.Step;
import todo.entity.Task;
import todo.service.TaskService;
import todo.service.StepService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (true) {
            System.out.println("Enter command (add task, add step, delete, update task, update step, get task-by-id, get all-tasks, get incomplete-tasks, exit):");
            String command = scanner.nextLine();

            try {
                switch (command) {
                    case "add task":
                        System.out.println("Enter task title:");
                        String taskTitle = scanner.nextLine();
                        System.out.println("Enter task description:");
                        String taskDescription = scanner.nextLine();
                        System.out.println("Enter due date (yyyy-MM-dd):");
                        String dueDateStr = scanner.nextLine();
                        Date dueDate = dateFormat.parse(dueDateStr);
                        TaskService.addTask(taskTitle, taskDescription, dueDate);
                        System.out.println("Task added successfully.");
                        break;

                    case "add step":
                        System.out.println("Enter task ID for the step:");
                        int taskRef = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter step title:");
                         String stepTitle = scanner.nextLine();
                        StepService.saveStep(taskRef, stepTitle);
                        System.out.println("Step added successfully.");
                        break;

                    case "delete":
                        System.out.println("Enter ID of task or step to delete:");
                        int entityId = Integer.parseInt(scanner.nextLine());
                        TaskService.deleteTask(entityId);
                        System.out.println("Entity deleted successfully.");
                        break;

                    case "update task":
                        System.out.println("Enter task ID to update:");
                        int taskIdToUpdate = Integer.parseInt(scanner.nextLine());
                        System.out.println("Enter new title:");
                        String newTaskTitle = scanner.nextLine();
                        TaskService.updateTaskTitle(taskIdToUpdate, newTaskTitle);
                        System.out.println("Task title updated successfully.");
                        break;


                    case "get task-by-id":
                        System.out.println("Enter task ID:");
                        int taskIdToGet = Integer.parseInt(scanner.nextLine());
                        Task task = (Task) Database.get(taskIdToGet);
                        System.out.println("Task: " + task.title + ", Description: " + task.description + ", Due Date: " + dateFormat.format(task.getDueDate()) + ", Status: " + task.getStatus());
                        break;

                    case "get all-tasks":
                        List<Entity> allTasks = Database.getAll(16);
                        for (Entity entity : allTasks) {
                            if (entity instanceof Task) {
                                Task t = (Task) entity;
                                System.out.println("ID: " + t.id + ", Title: " + t.title + ", Description: " + t.description + ", Due Date: " + dateFormat.format(t.getDueDate()) + ", Status: " + t.getStatus());
                            }
                        }
                        break;

                    case "get incomplete-tasks":
                        List<Entity> incompleteTasks = Database.getAll(16);
                        for (Entity entity : incompleteTasks) {
                            if (entity instanceof Task) {
                                Task t = (Task) entity;
                                if (t.getStatus() != Task.Status.Completed) {
                                    System.out.println("ID: " + t.id + ", Title: " + t.title + ", Description: " + t.description + ", Due Date: " + dateFormat.format(t.getDueDate()) + ", Status: " + t.getStatus());
                                }
                            }
                        }
                        break;

                    case "exit":
                        System.out.println("Exiting application.");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid command.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format. Please enter a number.");
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InvalidEntityException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
