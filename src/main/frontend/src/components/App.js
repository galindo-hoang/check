import React from "react";
import SearchBar from "./SearchBar";
import unsplash from "../api/employee";
class App extends React.Component{
    state = {images: []};
    constructor(props){
        super(props);
        this.onSearchSubmit = this.onSearchSubmit.bind(this);
    }
    async onSearchSubmit(term) {
        const respone = await unsplash.get('/employeeInfo',{
            params: {query: term},
        });
        this.setState({images: respone.data.results});
    }

    render(){
        return (
            <div className="ui container" style={{ marginTop: '10px' }}>
                <SearchBar onSubmitFromApp={this.onSearchSubmit} />
            </div>
        );
    }
}

export default App;