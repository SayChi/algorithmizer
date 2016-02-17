// As created by Bastiaan Wuisman on 23-2-2015
// Created using IntelliJ IDEA


import javax.swing.*;
import java.util.List;
import java.util.Random;

public class Algorithm {
	//Data structures
	Array A;
	MaxHeap MH;

	int type;
	int[] pointers;
	int delay;
	int n;
	long steps;

	GUI gui;

	Algorithm( int arraySize, int delaySet, final int algSelect ) {
		//init
		n = arraySize;
		delay = delaySet;
		gui = new GUI(this);

		//weird stuff with swing worker to make sure gui updates
		new SwingWorker<Void, Void>() {
			@Override
			//runs the actual algorithms
			protected Void doInBackground() throws Exception {
				switch(algSelect) {
					case 1:
						//Selection Sort
						generate(1000, 3, 1);

						for( int i = 0; i < A.a.length; i++ ) {
							updatePointer(0, i);

							int lowestKey = i;
							updatePointer(2, lowestKey);
							for( int j = i + 1; j < A.a.length; j++ ) {
								step();
								updatePointer(1, j);

								if( A.a[j] < A.a[lowestKey] ) lowestKey = j;
								updatePointer(2, lowestKey);
							}
							int swapTemp = A.a[i];
							A.a[i] = A.a[lowestKey];
							A.a[lowestKey] = swapTemp;
						}
						break;

					case 2:
						//Insertion Sort
						generate(1000, 2, 0);

						for( int i = 1; i < A.a.length; i++ ) {
							updatePointer(0, i);

							int key = A.a[i];
							int j;
							for( j = i - 1; j >= 0 && A.a[j] > key; j-- ) {
								step();
								updatePointer(1, j);

								A.a[j + 1] = A.a[j];
							}
							A.a[j + 1] = key;
						}
						break;

					case 3:
						//Bubble Sort
						generate(1000, 2, 0);

						for( int i = 0; i < A.a.length; i++ ) {
							for( int j = 1; j < A.a.length; j++ ) {
								step();
								updatePointer(0, j);
								updatePointer(1, j - 1);

								if( A.a[j - 1] > A.a[j] ) {
									int swapTemp = A.a[j];
									A.a[j] = A.a[j - 1];
									A.a[j - 1] = swapTemp;
								}
							}
						}
						break;

					case 4:
						//Quick Sort
						generate(1000, 5, 0);
						quickSort(0, A.a.length-1);
						break;

					case 5:
						//TODO: Heap Sort
						break;
				}
				for( int a : A.a ) System.out.println(a);
				return null;
			}

			//the recursive function for quickSort
			public void quickSort( int p, int r ) {
				updatePointer(0, p);
				updatePointer(1, r);
				step();
				if( p < r ) {
					int q = partition(p, r);
					updatePointer(4, q);
					step();
					quickSort(p, q);
					quickSort(q + 1, r);
				}
			}
			//even more shit for quickSort... gosh, quickSort is a needy bitch
			int partition( int p, int r ) {
				int x = A.a[p];
				int i = p - 1;
				int j = r + 1;

				while( true ) {
					i++;

					while( i < r && A.a[i] < x ) {
						i++;
						updatePointer(2, i);
						step();
					}

					j--;

					while( j > p && A.a[j] > x ) {
						j--;
						updatePointer(3, j);
						step();
					}

					if( i < j ) {
						swap(A.a, i, j);
					}else {
						return j;
					}
				}
			}

			//swaps the values at i and j in array
			void swap( int[] array, int i, int j ) {
				int temp = array[i];
				array[i] = array[j];
				array[j] = temp;
			}

			//initiates the chosen data structure and the correct amount of pointers
			void generate( int maxIntSize, int pointersAmount, int typeSet ) {
				type = typeSet;

				switch(type) {
					case 0:
						A = new Array(n, maxIntSize);
						break;

					case 1:
						MH = new MaxHeap(n , maxIntSize);
						break;
				}

				pointers = new int[pointersAmount];
				for( int i = 0; i < pointers.length; i++ ) {
					pointers[i] = -1;
				}
			}

			//increases step counter, updates gui and sleeps for the specified time
			void step() {
				steps++;
				publish();
				try {
					Thread.sleep(delay);
				}catch( InterruptedException e ) {
				}
			}

			//moves pointer 'updatePointer' to 'updatePointerVal'
			void updatePointer( int updatePointer, int updatePointerVal ) {
				pointers[updatePointer] = updatePointerVal;
			}

			@Override
			protected void process(List<Void> v) {
				gui.repaint();
			}
		}.execute();
	}
}

//Array data structure
class Array {
	int[] a;
	int max;
	Random r = new Random();

	//fills array with random data
	Array(int size, int maxIntSize) {
		max = maxIntSize;
		a = new int[size];
		for( int i = 0; i < a.length; i++ ) {
			a[i] = r.nextInt(max);
		}
	}

	//might need to be removed, not used yet anyways
	void Swap(int p, int q) {
		int temp = a[p];
		a[p] = a[q];
		a[q] = temp;
	}
}

//MaxHeap data structure
class MaxHeap {
	int[] a;
	int max;
	Random r = new Random();

	//fills array with random data
	MaxHeap(int size, int maxIntSize) {
		max = maxIntSize;
		a = new int[size];
		for( int i = 0; i < a.length; i++ ) {
			a[i] = r.nextInt(max);
		}
	}

	//TODO: add MaxHeap specific functions
}

//TODO: add more data structures