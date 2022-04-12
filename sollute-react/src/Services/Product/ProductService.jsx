import axios from 'axios';

const urlBase = process.env.REACT_APP_PAYGO_API_INTEGRATION;
const headers = {
  'Content-Type': 'application/json'
};
class ProductService {
  constructor() {
    this.state = {
      data: []
    };
  }

  async getPOSPagination(page, pageSize, filters = '') {
    return await axios.get(`${urlBase}/api/accreditation/point-of-sales?page=${page}&limit=${pageSize}${filters}`, {
      headers: headers
    })
      .then(res => res.data)
      .catch((err) => {
        console.error(`request failed ${err}`);
      });
  }
}


export default ProductService;