import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react'
import UsersList from './components/UsersList'


class App extends Component {
  render() {
    return (
      <div>
        <UsersList />
      </div>
    )
  }
}

export default App;
