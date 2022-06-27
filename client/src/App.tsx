import { gql, useQuery } from '@apollo/client';
import React, { useEffect, useState } from 'react';
import Task from './classes/Task';
import { Board } from './components/Board';
import { MakeTask } from './components/MakeTask';

const TASK_FETCH= gql`
query($compPage:Int!, $incPage:Int!, $pageSize:Int!){
  completeTask:
  getTaskByIsComplete(page:$compPage,size:$pageSize,isComplete:true){
    id
    name
    isCompleted
    creationDate
    completedDate
  }
  inCompleteTask:
  getTaskByIsComplete(page:$incPage,size:$pageSize,isComplete:false){
    id
    name
    isCompleted
    creationDate
    completedDate
  }
}
`;

type response = {
  completeTask: Array<Task>;
  inCompleteTask: Array<Task>;
}

type queryVariable = {
  compPage:number
}

function App() {
  const [baseCompPage, setCompPage] = useState(0);
  const [baseIncPage, setIncPage] = useState(0);
  const [basePageSize, setPageSize] = useState(8);
  const {loading, error, data, refetch} = useQuery<response>(TASK_FETCH, {
    variables: { compPage:baseCompPage, incPage:baseIncPage, pageSize:basePageSize },
  });
  

  const getData = () => {
    refetch();
  }

  useEffect(() => {
    getData();
  }, [])
  
  if(data)
    return (
      <div className="App">
        <Board tasks={data.completeTask}/>
        <Board tasks={data.inCompleteTask}/>
        <MakeTask />
        <button onClick={getData}>hit</button>
      </div>
    );

  return(
    <div>

    </div>
  );
}

export default App;
