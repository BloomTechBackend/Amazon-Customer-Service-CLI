# Amazon Customer Service CLI 


## Explaining Commit Comments
MT1:
- A CS representative has filed a bug report, stating that when they request the promise history for order ID 111-749023-7630574, the Missed Promise CLI prints a weird message and exits. Reproduce the bug and comment //FIXME where the fix should go.

MT2:
- Plant & implement unit tests for OrderDao.

MT3:
- Write a unit test that fails if the Order class has any externally modifiable variables.
- Encapsulate the Order class.

MT4:
- Redesign the PromiseDao to support multiple clients, including the OFS client we know about and future clients we can't anticipate right now.
- Implement the OrderFulfillmentServiceClient class to get the packaging promise from the OrderFulfillmentService.
- Update the PromiseDao to match the new design and retrieve the packaging promise from your OrderFulfillmentServiceClient. It should also use the App class to get its clients, rather than instantiating them itself. 

MT5:
- Modify the code so that it prints the promise(s) for each item in an order. Currently, the Missing Promise CLI displays promises for the first item in an order. That's not the whole story, though: Amazon often breaks orders into multiple shipments with different delivery speeds, or separates Amazon items from the items fulfilled by different vendors. Therefore, each item has a delivery promise.
- Sort the promises associated with the order to make it easier for CS reps to use the tool.


## Architecture Diagram
The Missed Promise CLI accepts an order ID from the CS representative and returns the order's information and promise history. Right now, the Missed Promise CLI will call the OrderingManipulationAuthority to get order information and the DeliveryPromiseService to get the Order Promise at the time of checkout.

A key objective of this project is to update the Missed Promise CLI so that it gets the Order Promise at the time of shipping from OrderFulfillmentService.

![ProjectArchitecture](https://user-images.githubusercontent.com/96976313/193474451-b2781318-5a26-429c-b8e2-8a50a12c91e9.png)

(This document will use these abbreviations going forward)

Missed Promise CLI: The command line interface to the CS rep that you'll be updating.

Delivery Promise Service (DPS): Service providing the delivery promise made to the customer at checkout. The Missed Promise CLI already uses the GetDeliveryPromise API to fetch the checkout delivery promise.

Order Fulfillment Service (OFS): Service providing the delivery promise made at the time of packaging the order in a fulfillment center. You will use the GetFulfillmentPromise API to retrieve the promise(s) at the time of shipping. See the Appendix for a sample OFS Fulfillment Promise Result.

Ordering Manipulation Authority (OMA): Service that creates and stores customer's orders. The Missed Promise CLI already uses the GetCustomerOrderByOrderId API to fetch a full order object by order ID. See the Appendix in the README file for a sample Order and OrderItem.

## The Delivering on our Promise Sequence Diagram
There are two components to this application:

1. The interactive shell that receives and presents information to the user
2. Get Promise History By Order ID API: The back-end logic to collect the order's promise history from an order ID

### 1. Interactive Shell
The main method starts the Shell class, which interacts directly with the user, forwarding calls to the API. The Shell class collects the order ID from the user through the terminal, calls the API to get the order's promise history, and displays it on the screen. It repeats this until the user is done, at which point the program will terminate. This project modifies the API that the Shell class calls to fetch the order history.

### 2. Get Promise History by Order Id API
 The project modifies this API to provide the interactive shell with the OrderPromiseHistory. This sequence diagram shows the current interaction between the relevant classes.
 ![OverviewSD](https://user-images.githubusercontent.com/96976313/193474592-d842bae9-9f5f-4dde-9468-23b3d57ba62a.png).
 
