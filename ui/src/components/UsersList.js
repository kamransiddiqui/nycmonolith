import React, {Component} from 'react'
import axios from 'axios'

import User from './User'

class UsersList extends Component {
	state = {
		users: []
	}

	async componentWillMount() {
        try {
            const response = await axios.get('/users')
            this.setState({ users: response.data })
        } catch (error) {
            console.log('Error retrieving users!')
            console.log(error)
        }
    }
	render() {
	    return (
	        <div>
	            <h1>Users list Board</h1>
	            {
                this.state.ideas.map((User) => {
                    return (
                        <User
                            {...user}
                            key={index} />
                    )                    })
                }
	        </div>
	    )
	}
}

export default UsersList