import React, {Component} from 'react';
import './style.css'

class CountryList extends Component<any, any> {
  // declaring the default state
  state = {
    sortBy: 'name',
    currentSort: 'asc',
    countries: []
  };

  async getCountries(sortBy: string, sortOrder: string) {
    let url = "http://localhost:8080/v1/country/list?sort=" + sortBy + ":" + sortOrder + "&offset=0&max=400 "
    console.log(url)
    const res = await fetch(url);
    return res.json();
  }

  async componentDidMount() {
    const countries = await this.getCountries(this.state.sortBy, this.state.currentSort)
    .catch(console.error);
    console.log(countries)
    this.setState({countries});
  }

  renderDocuments(documents: any[]) {
    return documents?.map((document) => (
        <li key={document['id']}>
          {document['description']}
        </li>
    ));
  }


  // method called every time the sort button is clicked
  // it will change the currentSort value to the next one
  onSortChange = async (sortBy: string) => {
    const {currentSort} = this.state;
    let nextSort;

    if (currentSort === 'asc') nextSort = 'desc';
    else if (currentSort === 'desc') nextSort = 'asc';
    console.log("SortBy : " + sortBy + " current sort " + currentSort + " nextSort " + nextSort);
    const countries = await this.getCountries(sortBy, currentSort)
    this.setState({
      sortBy: sortBy,
      currentSort: nextSort,
      countries: countries
    });
  };

  render() {
    const currentSort = this.state.currentSort;
    const sortBy = this.state.sortBy
    console.log(currentSort)
    let buttonClass;
    if (currentSort === "asc") {
      buttonClass = "arrow down"
    } else {
      buttonClass = "arrow up"
    }
    console.log(buttonClass)
    return (
        this.state.countries.length > 0 && (
            <div>
              Sort by
              <select onChange={(e) => this.onSortChange(e.target.value)}>
                <option value="name">Name</option>
                <option value="currency">Currency</option>
              </select>
              <button onClick={() => this.onSortChange(sortBy)}>
                <i id="sort" className={buttonClass}/>
              </button>
              <table className='text-left'>
                <thead>
                <tr>
                  <th>
                    Name
                  </th>
                  <th>
                    Currency
                  </th>
                  <th>
                    Max Withdrawal
                  </th>
                  <th>
                    Required Documents
                  </th>


                </tr>
                </thead>
                <tbody>
                {this.state.countries.map((country) => (
                    <tr key={country['code']}>
                      <td>{country['name']}</td>
                      <td>{country['currency']}</td>
                      <td>{country['maxWithdrawal']}</td>
                      <td>
                        {this.renderDocuments(country['requiredDocuments'])}
                      </td>
                    </tr>

                ))}
                </tbody>
              </table>
            </div>
        )
    );
  }
}

export default CountryList