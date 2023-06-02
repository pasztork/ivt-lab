| No. | Test description |
|:----------:|:-------------|
| 1 | When firing in SINGLE mode, first the primary, then the secondary store is fired. |
| 2 | If the primary store is empty, the secondary tries to fire. |
| 3 | If both stores are empty, the ship doesn't fire. |
| 4 | When at least one store fired, the method returns true. |
| 5 | When neither one of the stores fired, the methods returns false. |
| 6 | When at least one store is empty, firing in ALL mode fails. |