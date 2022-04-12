import axios from 'axios';

const urlBase = 'http://localhost:8080/empresas';
const headers = {
  'Content-Type': 'application/json'
};
class ProductService {
  constructor() {
    this.state = {
      data: []
    };
  }

  async getProdutos() {
    return await axios.get(`http://localhost:8080/empresas/listar-produtos-vestuario/55756157000133`, {
      headers: headers
    })
      .then(res => res.data)
      .catch((err) => {
        console.error(`request failed ${err}`);
      });
  }
}


export default ProductService;