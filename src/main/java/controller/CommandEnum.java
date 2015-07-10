package controller;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 03.06.15
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */


import controller.commands.*;

/**
 * enum with names of classes
 */
public enum CommandEnum {

    SCHEDULE {
        {
            this.command = new ScheduleCommand();
        }
    },
    NEW_TEACHER {
        {
            this.command = new TeacherCommand();
        }
    },
    NEW_ROOM {
        {
            this.command = new RoomCommand();
        }
    },
    NEW_STUDENT {
        {
            this.command = new StudentCommand();
        }
    },
    LESSON_DATA {
        {
            this.command = new LessonDataCommand();
        }
    },
    NEW_LESSON {
        {
            this.command = new LessonCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
