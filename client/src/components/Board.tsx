import Task from "../classes/Task";
import {TaskBox} from "./TaskBox";

type BoardProps = {
    tasks:Array<Task>
}

export function Board(props:BoardProps){

    const getTask = () => {
        return props.tasks.map(task =>{
            return <TaskBox task={task}/>
        });
    }

    return(
        <div>
            {getTask()}
        </div>
    );
}