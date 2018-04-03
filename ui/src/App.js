import React, { Component } from 'react';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';
import axios from 'axios'

import UsersList from './components/UsersList'
import UserNewForm from './components/UserNewForm'
import Home from './components/Home'


class App extends Component {
  state = {
  		users: []
  	}
  	  createUser = async (newUser) => {
          try {
              const newUserResponse = await axios.post(`/users`, newUser)
              const newUserFromDatabase = newUserResponse.data;
              const updatedUsersList = [...this.state.users]
              updatedUsersList.push(newUserFromDatabase)
              this.setState({users: updatedUsersList})

          } catch(error) {
              console.log('Error creating new User!')
          }
      }
      deleteUser = async (userId, index) => {
          try {
              await axios.delete(`/users/${userId}`)

              const updatedUsersList = [...this.state.users]
              updatedUsersList.splice(index, 1)
              this.setState({users: updatedUsersList})

          } catch (error) {
              console.log(`Error deleting User with ID of ${userId}`)
          }
      }
      updateUser = async (index) => {
          try {
              const userToUpdate = this.state.users[index]
              await axios.patch(`/users/${userToUpdate.id}`, userToUpdate)
          } catch(error) {
              console.log('Error updating user!')
              console.log(error)
          }
      }

      async componentDidMount() {
        try {
            const response = await axios.get('/users')
            this.setState({ users: response.data })
        } catch (error) {
            console.log('Error retrieving users!')
            console.log(error)
        }
      }

      handleChange = (event) => {
          const attributeToChange = event.target.name
          const newValue = event.target.value

          const updatedNewUsers = { ...this.state.newUser }
          updatedNewUsers[attributeToChange] = newValue
          this.setState({ newUser: updatedNewUsers })
      }

   render() {
    const UserListComponent = () => (
        <UsersList
          users={this.state.users}
          deleteUser={this.deleteUser}
          handleUserChange={this.handleUserChange}
          updateUser={this.updateUser} />
    )

    const NewUserComponent = () => <UserNewForm createUser={this.createUser} />
    const HomePageComponent = () => <Home currentUser={this.state.users} userLogin={this.userLogin}/>


    return (
      <div>
        <h1>Home</h1>
        <Router>
          <Switch>
            <Route exact path="/" render={HomePageComponent}/>
            <Route exact path="/users" render={UserListComponent}/>
            <Route exact path="/newuser" render={NewUserComponent}/>
          </Switch>
        </Router>
      </div>
    )
  }
}

export default App;
