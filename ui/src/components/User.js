import React, {Component} from 'react'

class User extends Component {

    render() {
        return (
            <div>
                <div>{this.props.title}</div>
                <div>{this.props.description}</div>
                <hr/>
            </div>
        )
    }

}

export default User