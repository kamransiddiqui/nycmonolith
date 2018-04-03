import React, { Component } from 'react'
import { Link } from 'react-router-dom'

class UserNewForm extends Component {

    state = {
        newUser: {}
    }

    handleChange = (event) => {
        const attributeToChange = event.target.name
        const newValue = event.target.value

        const updatedNewUser = { ...this.state.newUser }
        updatedNewUser[attributeToChange] = newValue
        this.setState({ newUser: updatedNewUser })
    }

    handleSubmit = (event) => {
        event.preventDefault()

        this.props.createUser(this.state.newUser)
        this.setState({newUser:{}})
    }

    render() {
        return (
            <div>
                <h2>Create New User</h2>

                <form onSubmit={this.handleSubmit}>
                    <div>
                        <label htmlFor="userName">Username</label>
                        <input
                            name="username"
                            type="text"
                            onChange={this.handleChange} />
                    </div>

                    <div>
                        <label htmlFor="firstName">Firstname</label>
                        <input
                            name="firstName"
                            type="text"
                            onChange={this.handleChange} />
                    </div>

                    <div>
                        <label htmlFor="lastName">Lastname</label>
                        <input
                            name="lastName"
                            type="text"
                            onChange={this.handleChange} />
                    </div>
                    <div>
                        <label htmlFor="email">Email</label>
                        <input
                            name="email"
                            type="text"
                            onChange={this.handleChange} />
                    </div>

                    <div>
                        <input type="submit" value="Create User"/>
                    </div>
                </form>
                <h1>Menu</h1>

                  <Link to='/'> Home  </Link>
                  <Link to="/users" id="users-link"> Users </Link>

                <hr />
                <hr />
            </div>
        )

    }

}

export default UserNewForm