export type Product = {
	id: number;
	name: string;
	color: string;
	description: string;
	price: number;
	seasoning: string;
	image: string | null;
};

export type Order = {
	id: number;
	products: { productID: number; quantity: number }[];
	status: string;
	total: number;
	createdAt: number;
};
