import { gql, useMutation } from "@apollo/client";
import React, { useEffect, useState } from 'react';

const TASK_MAKE= gql`
mutation($name:String!){
    createTask(input:{
      name:$name
    })
  }
`;

export const MakeTask = () => {
    const [baseName, setName] = useState("tedfdsfst");
    const[ makeTask, {loading, error, data}] = useMutation(TASK_MAKE);

    // const handleChange = (e) => {
    //     setName({target});
    // }

    // const submitTaskName = (e) => {
    //     setName(e.target.value);
    //     fetch();
    // }

    const fetch = () =>{
        makeTask({ variables: { name:baseName }});
    }


    return(
        <div>
            <input className="name" value="e" >

            </input>
            <button onClick={fetch}>
                add task
            </button>
        </div>
    );
}