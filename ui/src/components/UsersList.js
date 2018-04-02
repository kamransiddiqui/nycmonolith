import React, {Component} from 'react'
import axios from 'axios'

class UsersList extends Component {
	state = {
		users: []
	}

	async componentWillMount() {
        try {
            const response = await axios.get('/users')
            this.setState({ users: response.data })
        } catch (error) {
            console.log('Error retrieving ideas!')
            console.log(error)
        }
    }
	render() {
	    return (
	        <div>
	            <h1>Users list Board</h1>
	        </div>
	    )
	}
}

export default UsersList