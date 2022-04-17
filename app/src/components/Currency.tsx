import React, {Component} from 'react';
import '../App.css';

class Currency extends Component<any, any> {
  constructor(props: any) {
    super(props);

    this.state = {
      currencies: [],
    };

    this.getCurrency = this.getCurrency.bind(this);
  }

  async getCurrency() {
    const res = await fetch(`http://localhost:8080/v1/country/currency`);
    return res.json();
  }

  async componentDidMount() {
    const currencies = await this.getCurrency()
    .catch(console.error);
    console.log(currencies)
    this.setState({currencies});
  }

  render() {
    return (
        <div>
          <table>
            <thead>
            <tr>
              <th>Country</th>
              <th>Currency</th>
            </tr>
            </thead>
            <tbody>
            {this.state.currencies.map((currency) => (
                <tr>
                  <td>{currency.currency}</td>
                  <td>{currency.countryName}</td>
                </tr>

            ))}
            </tbody>
          </table>
        </div>
    );
  }
}

export default Currency;