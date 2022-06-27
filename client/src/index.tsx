import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

import {
  ApolloClient,
  InMemoryCache,
  ApolloProvider,
  useQuery,
  gql
} from "@apollo/client";

const client = new ApolloClient({
  uri: 'http://localhost:8080/graphql',
  cache: new InMemoryCache()
});

client
  .query({
    query: gql`
    query{
      completeTask:
      getTaskByIsComplete(page:0,size:3,isComplete:true){
        id
        name
        isCompleted
        creationDate
        completedDate
      }
      inCompleteTask:
      getTaskByIsComplete(page:0,size:3,isComplete:false){
        id
        name
        isCompleted
        creationDate
        completedDate
      }
    }
    `
  })
  .then(result => console.log(result));

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <ApolloProvider client={client}>
      <App />
    </ApolloProvider>
  </React.StrictMode>
);
