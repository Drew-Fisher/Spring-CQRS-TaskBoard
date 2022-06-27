import { gql, useMutation } from '@apollo/client';
import { type } from '@testing-library/user-event/dist/type';
import React, { useEffect, useState } from 'react';
import Task from '../classes/Task';
type TaskBoxProps = {
    task:Task
}

const COMPLETE_TASK= gql`
mutation($id:ID!){
    completeTask(input:{
      id:$id
    })
  }
`;

export const TaskBox = (props:TaskBoxProps) =>{
    let v = props.task.id;
    const [bid,setid] = useState(props.task.id);
    const [completeTask, {loading,error,data}] = useMutation(COMPLETE_TASK);

    const mutate = () =>{
        console.log(props.task.id)
        console.log(v)
        completeTask({ variables: { id:props.task.id }});
    }

    return(
        <div>
            <label className="taskName">{props.task.name}</label>
            <button className='completeButton' onClick={mutate}>complete</button>
        </div>
    );
}