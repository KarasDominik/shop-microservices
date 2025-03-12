## Architecture
![Image](https://github.com/user-attachments/assets/2a5db15f-dcc4-4970-a42c-99b437f3aba1)
## Technologies used:
- **Backend:**
  - Spring Boot, Spring Cloud, Spring Data JPA, Resilience4j, Spring Security
- **Database:**
  - MySQL, MongoDB
- **Deployment:**
  - Docker
  - Kubernetes
- **CI/CD:**
  - GitHub Actions
- **Async communication:**
  - Kafka
- **Security:**
  - Keycloak
## Getting started
1. Clone the repository
2. To create kubernetes cluster, go into <code>k8s/kind</code> directory and run <code>bash create-kind-cluster.sh</code>
3. To deploy infrastructure, go into <code>k8s/manifests</code> directory and run <code>kubectl apply -f /infrastructure</code>
4. To deploy services, from <code>k8s/manifests</code> directory run <code>kubectl apply -f application</code>
